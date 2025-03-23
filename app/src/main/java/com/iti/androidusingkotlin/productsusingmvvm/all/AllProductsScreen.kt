package com.iti.androidusingkotlin.productsusingmvvm.all

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse
import com.iti.androidusingkotlin.productsusingmvvm.ui.screens.ProductsScreen
import com.iti.androidusingkotlin.productsusingmvvm.ui.states.ProductsScreenUiState

@Composable
fun AllProductsScreen(
    state: ProductsScreenUiState,
    onFavoriteButtonClicked: (ProductsResponse.Product) -> Unit,
    modifier: Modifier = Modifier
) {
    ProductsScreen(
        state = state,
        onClickOnProduct = onFavoriteButtonClicked,
        modifier = modifier,
        actionText = "Add To Favorite"
    )

}

@Preview
@Composable
private fun AllProductsScreenPreview() {

}