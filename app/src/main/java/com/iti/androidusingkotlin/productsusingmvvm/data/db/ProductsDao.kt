package com.iti.androidusingkotlin.productsusingmvvm.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductsResponse.Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: ProductsResponse.Product)

    @Delete
    suspend fun deleteProduct(product: ProductsResponse.Product)


}