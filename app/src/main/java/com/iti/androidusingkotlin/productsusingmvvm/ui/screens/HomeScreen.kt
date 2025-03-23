package com.iti.androidusingkotlin.productsusingmvvm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iti.androidusingkotlin.productsusingmvvm.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onRemoteButtonClicked: () -> Unit,
    onFavoriteButtonClicked: () -> Unit,
    onFinishButtonClicked: () -> Unit
) {

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "",
            modifier = Modifier.size(200.dp)
        )

        Button(onClick = onRemoteButtonClicked) {
            Text("Products From Remote")
        }

        Button(onClick = onFavoriteButtonClicked) {
            Text("Products From Favorite")
        }

        Button(onClick = onFinishButtonClicked) {
            Text("Finish")
        }
    }


}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onRemoteButtonClicked = {  },
        onFavoriteButtonClicked = {  },
        onFinishButtonClicked = {  }
    )
}