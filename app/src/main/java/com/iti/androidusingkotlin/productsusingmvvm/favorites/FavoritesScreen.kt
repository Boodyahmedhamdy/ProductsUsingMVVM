package com.iti.androidusingkotlin.productsusingmvvm.favorites

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse
import com.iti.androidusingkotlin.productsusingmvvm.ui.screens.ProductsScreen
import com.iti.androidusingkotlin.productsusingmvvm.ui.states.ProductsScreenUiState

@Composable
fun FavoriteScreen(
    state: ProductsScreenUiState,
    onDeleteButtonClicked: (ProductsResponse.Product) -> Unit,
    modifier: Modifier = Modifier
) {
    ProductsScreen(
        state = state,
        onClickOnProduct = onDeleteButtonClicked,
        modifier = modifier,
        actionText = "Delete"
    )
}