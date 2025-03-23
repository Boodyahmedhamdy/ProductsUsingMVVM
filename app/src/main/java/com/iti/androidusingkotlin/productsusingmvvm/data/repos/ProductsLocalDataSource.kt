package com.iti.androidusingkotlin.productsusingmvvm.data.repos

import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse
import com.iti.androidusingkotlin.productsusingmvvm.data.db.ProductsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

class ProductsLocalDataSource(
    private val productsDao: ProductsDao
) {
    suspend fun getProducts(): Flow<List<ProductsResponse.Product>> = withContext(Dispatchers.IO) {
        productsDao.getAllProducts()
    }

    suspend fun addProduct(product: ProductsResponse.Product) = withContext(Dispatchers.IO) {
        productsDao.insertProduct(product)
    }

    suspend fun deleteProduct(product: ProductsResponse.Product) = withContext(Dispatchers.IO) {
        productsDao.deleteProduct(product)
    }



}
