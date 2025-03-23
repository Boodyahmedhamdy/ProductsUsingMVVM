package com.iti.androidusingkotlin.productsusingmvvm.all.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iti.androidusingkotlin.productsusingmvvm.data.repos.ProductsRepository

class AllProductsViewModelFactory(
    private val repo: ProductsRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllProductsViewModel(repo) as T
    }
}