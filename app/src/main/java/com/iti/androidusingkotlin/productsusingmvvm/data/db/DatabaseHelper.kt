package com.iti.androidusingkotlin.productsusingmvvm.data.db

import android.content.Context

object DatabaseHelper {

    private fun getDatabase(context: Context) = ProductsDatabase.getInstance(context)

    fun getProductsDao(context: Context) = getDatabase(context).getProductsDao()

}