package com.iti.androidusingkotlin.productsusingmvvm.all.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse
import com.iti.androidusingkotlin.productsusingmvvm.data.Result
import com.iti.androidusingkotlin.productsusingmvvm.data.repos.ProductsRepository
import com.iti.androidusingkotlin.productsusingmvvm.ui.states.ProductsScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "AllProductsViewModel"
class AllProductsViewModel(
    private val repo: ProductsRepository
): ViewModel() {

    private val _state: MutableStateFlow<ProductsScreenUiState> = MutableStateFlow(
        ProductsScreenUiState()
    )
    val state: StateFlow<ProductsScreenUiState> = _state.asStateFlow()

    init {
        Log.i(TAG, "view model started")
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repo.getProducts(isOnline = true)
                .catch {throwable ->
                    _state.update {
                        it.copy(msg = throwable.message ?: "ERROR")
                    }
                    Log.i(TAG, "getProducts: in catch")
                }
                .collect { result ->
                    when(result) {
                        is Result.Failure -> {
                            _state.update {
                                it.copy(msg = result.error.message ?: "ERROR", isLoading = false)
                            }
                        }
                        Result.Loading -> {
                            _state.update { it.copy(isLoading = true) }
                        }
                        is Result.Success -> {
                            _state.update { it.copy(isLoading = false, products = result.data) }
                        }
                    }
            }



        }
    }

    fun addProductToFavorite(product: ProductsResponse.Product) {
        viewModelScope.launch {
            try {
                repo.addProductToFavorite(product)
                _state.update { it.copy(msg = "Successfully added ${product.title} to favorite") }
            } catch (ex: Exception) {
                _state.update { it.copy(msg = "${ex.message} ERROR in Favorite") }
            }
        }
    }


}