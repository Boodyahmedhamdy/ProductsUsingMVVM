package com.iti.androidusingkotlin.productsusingmvvm.favorites.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse
import com.iti.androidusingkotlin.productsusingmvvm.data.Result
import com.iti.androidusingkotlin.productsusingmvvm.data.repos.ProductsRepository
import com.iti.androidusingkotlin.productsusingmvvm.ui.states.ProductsScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "FavoritesViewModel"
class FavoritesViewModel(
    private val repo: ProductsRepository
): ViewModel() {
    private val _state: MutableStateFlow<ProductsScreenUiState> = MutableStateFlow(ProductsScreenUiState())
    val state: StateFlow<ProductsScreenUiState> = _state.asStateFlow()

    init {
        Log.i(TAG, "view model started")
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
//            _state.postValue(_state.value?.copy(isLoading = true) ?: _state.value)
            _state.update { it.copy(isLoading = true) }
            repo.getProducts(isOnline = false)
                .flowOn(Dispatchers.IO)
                .catch {throwable ->
                    _state.update { it.copy(isLoading = false, msg = throwable.message ?: "ERROR" ) }
                }
                .collect {result ->
                    when(result) {
                        is Result.Failure -> {
                            _state.update { it.copy(isLoading = false, msg = "Failed to load data") }
                        }
                        Result.Loading -> { _state.update { it.copy(isLoading = true) } }
                        is Result.Success -> {
                            _state.update {
                                it.copy(isLoading = false, products = result.data)
                            }
                        }
                    }
                }
        }
    }

    fun deleteProduct(product: ProductsResponse.Product) {
        viewModelScope.launch {
            try {
                repo.deleteProductFromFavorite(product)
            } catch(ex: Exception) {
                _state.update {
                    it.copy(isLoading = false, msg = ex.message ?: "ERROR while deleting")
                }
            }
        }
    }

}