package com.iti.androidusingkotlin.day4.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.iti.androidusingkotlin.productsusingmvvm.R


import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductCard(
    state: ProductsResponse.Product,
    onClick: (product: ProductsResponse.Product) -> Unit,
    modifier: Modifier = Modifier,
    actionText: String = "",
) {

    Row(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        GlideImage(
            model = state.thumbnail,
            contentDescription = state.title,
            loading = placeholder(R.drawable.ic_launcher_foreground),
            failure = placeholder(R.drawable.ic_launcher_background),
            modifier = Modifier.size(120.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = state.title, style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = state.description, style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.alpha(0.7f),
                maxLines = 3,
            )

            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { onClick(state) }
            ) {
                Text(actionText)
            }
        }
    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProductCardPreview() {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(5) {
            ProductCard(
                state = ProductsResponse.Product(
                    title = "Product Title",
                    description = "sldfjsldfjdlfjsdlkfjsdlfjlsdfjlsdldsfjkjlsfj",
                    price = 3.12
                ),
                onClick = {},
                modifier = Modifier.padding(16.dp),
                actionText = "Add To Favorite"
            )
        }
    }

}

