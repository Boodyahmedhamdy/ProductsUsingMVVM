package com.iti.androidusingkotlin.productsusingmvvm.data.network

import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object API {
    val service: ProductsService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsService::class.java)
    }
}

interface ProductsService {

    @GET("products")
    suspend fun getAllProducts(): Response<ProductsResponse>

}