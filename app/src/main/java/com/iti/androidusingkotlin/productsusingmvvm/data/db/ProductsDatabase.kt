package com.iti.androidusingkotlin.productsusingmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse

@Database(entities = arrayOf(ProductsResponse.Product::class), version = 1)
abstract class ProductsDatabase: RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao

    companion object {
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        fun getInstance(context: Context): ProductsDatabase {
            return INSTANCE ?: synchronized(this) {
                val temp =  Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "products_db"
                ).build()
                INSTANCE = temp
                temp
            }
        }
    }

}