package com.iti.androidusingkotlin.productsusingmvvm.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products")
    val products: List<Product> = listOf(),
) {


    @Entity(tableName = "products")
    data class Product(

        @SerializedName("category")
        val category: String = "",
        @SerializedName("description")
        val description: String = "",

        @SerializedName("id")
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,

        @SerializedName("price")
        val price: Double = 0.0,
        @SerializedName("rating")
        val rating: Double = 0.0,

        @SerializedName("thumbnail")
        val thumbnail: String = "",
        @SerializedName("title")
        val title: String = ""
    )
}