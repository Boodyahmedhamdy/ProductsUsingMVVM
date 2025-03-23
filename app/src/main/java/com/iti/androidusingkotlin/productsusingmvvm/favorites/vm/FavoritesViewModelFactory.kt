package com.iti.androidusingkotlin.productsusingmvvm.favorites.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iti.androidusingkotlin.productsusingmvvm.data.repos.ProductsRepository

class FavoritesViewModelFactory(
    private val repo: ProductsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritesViewModel(repo) as T
    }
}