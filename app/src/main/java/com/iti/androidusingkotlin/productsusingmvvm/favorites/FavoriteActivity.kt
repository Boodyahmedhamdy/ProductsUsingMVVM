package com.iti.androidusingkotlin.productsusingmvvm.favorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iti.androidusingkotlin.productsusingmvvm.data.db.DatabaseHelper
import com.iti.androidusingkotlin.productsusingmvvm.data.network.API
import com.iti.androidusingkotlin.productsusingmvvm.data.repos.ProductsLocalDataSource
import com.iti.androidusingkotlin.productsusingmvvm.data.repos.ProductsRemoteDataSource
import com.iti.androidusingkotlin.productsusingmvvm.data.repos.ProductsRepository
import com.iti.androidusingkotlin.productsusingmvvm.favorites.vm.FavoritesViewModel
import com.iti.androidusingkotlin.productsusingmvvm.favorites.vm.FavoritesViewModelFactory
import com.iti.androidusingkotlin.productsusingmvvm.ui.states.ProductsScreenUiState
import com.iti.androidusingkotlin.productsusingmvvm.ui.theme.ProductsUsingMVVMTheme
import kotlinx.coroutines.launch

class FavoriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsUsingMVVMTheme {
                val factory = FavoritesViewModelFactory(
                    ProductsRepository(
                        localDataSource = ProductsLocalDataSource(
                            DatabaseHelper.getProductsDao(this)
                        ),
                        remoteDataSource = ProductsRemoteDataSource(API.service)
                    )
                )
                val viewmodel: FavoritesViewModel = viewModel(factory = factory)
                val state = viewmodel.state.collectAsStateWithLifecycle()

                val scope = rememberCoroutineScope()
                val snackBarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
                ) { innerPadding ->

                    FavoriteScreen(
                        state = state.value,
                        onDeleteButtonClicked = {
                            viewmodel.deleteProduct(it)
                        },
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )

                    LaunchedEffect(state.value.msg) {
                        if(state.value.msg.isNotBlank()) {
                            scope.launch {
                                snackBarHostState.showSnackbar(state.value.msg)
                            }
                        }

                    }

                }
            }
        }
    }
}

