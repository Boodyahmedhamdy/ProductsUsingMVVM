package com.iti.androidusingkotlin.productsusingmvvm.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iti.androidusingkotlin.day4.ui.components.ProductCard
import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse
import com.iti.androidusingkotlin.productsusingmvvm.ui.states.ProductsScreenUiState

private const val TAG = "ProductsScreen"
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsScreen(
    state: ProductsScreenUiState,
    onClickOnProduct: (ProductsResponse.Product) -> Unit,
    actionText: String,
    modifier: Modifier = Modifier,
) {

    if(state.isLoading) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.padding(horizontal = 8.dp),
        ) {
            items(
                state.products,
                key = { it.id }
            ) {
                ProductCard(
                    state = it,
                    onClick = onClickOnProduct,
                    actionText = actionText,
                    modifier = Modifier.animateItemPlacement()
                )
            }
        }
    }




}


@Preview
@Composable
private fun ProductsScreenPreview() {

}