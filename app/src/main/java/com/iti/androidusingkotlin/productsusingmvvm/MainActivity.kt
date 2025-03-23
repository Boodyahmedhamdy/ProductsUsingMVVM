package com.iti.androidusingkotlin.productsusingmvvm

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.iti.androidusingkotlin.productsusingmvvm.all.AllProductsActivity
import com.iti.androidusingkotlin.productsusingmvvm.favorites.FavoriteActivity
import com.iti.androidusingkotlin.productsusingmvvm.ui.screens.HomeScreen
import com.iti.androidusingkotlin.productsusingmvvm.ui.theme.ProductsUsingMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductsUsingMVVMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        onRemoteButtonClicked = {
                            Intent(this, AllProductsActivity::class.java).also {
                                startActivity(it)
                            }
                        },
                        onFavoriteButtonClicked = {
                            Intent(this, FavoriteActivity::class.java).also {
                                startActivity(it)
                            }
                        },
                        onFinishButtonClicked =  { finish() },
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }
    }
}
