package com.iti.androidusingkotlin.productsusingmvvm.ui.states


import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse

data class ProductsScreenUiState(
    val products: List<ProductsResponse.Product> = listOf(),
    val msg: String = "",
    val isLoading: Boolean = false
)
