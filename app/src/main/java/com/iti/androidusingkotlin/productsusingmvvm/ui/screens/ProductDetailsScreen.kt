package com.iti.androidusingkotlin.day5.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.iti.androidusingkotlin.productsusingmvvm.R
import com.iti.androidusingkotlin.productsusingmvvm.data.ProductsResponse

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailsScreen(
    state: ProductsResponse.Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        GlideImage(
            model = state.thumbnail,
            contentDescription = state.title,
            loading = placeholder(R.drawable.ic_launcher_foreground),
            failure = placeholder(R.drawable.ic_launcher_background),
            modifier = Modifier.size(120.dp)
        )

        Text(
            text = state.title,
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(Modifier.height(16.dp))

        Text(
            text = state.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.alpha(0.7f)
        )
        Spacer(Modifier.height(16.dp))

        Text(
            text = "${state.price} $",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(16.dp))




    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProductDetailsScreenPreview() {

    ProductDetailsScreen(
        state = ProductsResponse.Product(
            "Category",
            description = "slkjdflksjdflsdjflkdsjlksdjflkdsjfldsj",
            price = 22.4,
            title = "Title Product"
        ),
        modifier = Modifier.fillMaxSize()
    )

}