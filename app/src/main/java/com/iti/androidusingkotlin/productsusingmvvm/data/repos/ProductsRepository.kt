package com.iti.androidusingkotlin.productsusingmvvm.data.repos

import android.util.Log
import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse
import com.iti.androidusingkotlin.productsusingmvvm.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.toList

private const val TAG = "ProductsRepository"
class ProductsRepository(
    private val localDataSource: ProductsLocalDataSource,
    private val remoteDataSource: ProductsRemoteDataSource
) {

    suspend fun getProducts(isOnline: Boolean): Flow<Result<List<ProductsResponse.Product>>>  = flow {
        emit(Result.Loading)
        if(isOnline) {
            val data = remoteDataSource.getProducts()
            Log.i(TAG, "getProducts: data is ${data.size} products")
            emit(Result.Success(data))
        } else {
            localDataSource.getProducts().collect {
                emit(Result.Success(it))
            }
        }
    }.flowOn(Dispatchers.IO)



    suspend fun addProductToFavorite(product: ProductsResponse.Product) = localDataSource.addProduct(product)

    suspend fun deleteProductFromFavorite(product: ProductsResponse.Product) = localDataSource.deleteProduct(product)
}