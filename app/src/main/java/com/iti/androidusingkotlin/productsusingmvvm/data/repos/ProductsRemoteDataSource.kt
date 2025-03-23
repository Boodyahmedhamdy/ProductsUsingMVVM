package com.iti.androidusingkotlin.productsusingmvvm.data.repos

import com.iti.androidusingkotlin.productsusingmvvm.data.network.ProductsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsRemoteDataSource (
    private val productsService: ProductsService
){
    suspend fun getProducts() = withContext(Dispatchers.IO) {
        productsService.getAllProducts().body()?.products ?: listOf()
    }


}
