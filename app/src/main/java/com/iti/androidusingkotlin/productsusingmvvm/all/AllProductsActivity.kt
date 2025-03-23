package com.iti.androidusingkotlin.productsusingmvvm.all

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iti.androidusingkotlin.productsusingmvvm.all.vm.AllProductsViewModel
import com.iti.androidusingkotlin.productsusingmvvm.all.vm.AllProductsViewModelFactory
import com.iti.androidusingkotlin.productsusingmvvm.data.db.DatabaseHelper
import com.iti.androidusingkotlin.productsusingmvvm.data.network.API
import com.iti.androidusingkotlin.productsusingmvvm.data.repos.ProductsLocalDataSource
import com.iti.androidusingkotlin.productsusingmvvm.data.repos.ProductsRemoteDataSource
import com.iti.androidusingkotlin.productsusingmvvm.data.repos.ProductsRepository
import com.iti.androidusingkotlin.productsusingmvvm.ui.states.ProductsScreenUiState
import com.iti.androidusingkotlin.productsusingmvvm.ui.theme.ProductsUsingMVVMTheme
import kotlinx.coroutines.launch

private const val TAG = "AllProductsActivity"
class AllProductsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductsUsingMVVMTheme {
                val factory = AllProductsViewModelFactory(
                    ProductsRepository(
                        localDataSource = ProductsLocalDataSource(
                            DatabaseHelper.getProductsDao(this)
                        ),
                        remoteDataSource = ProductsRemoteDataSource(API.service))
                )
                val viewmodel: AllProductsViewModel = viewModel(factory = factory)
                val state = viewmodel.state.collectAsStateWithLifecycle()

                val scope = rememberCoroutineScope()
                val snackBarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackBarHostState)
                    }
                ) { innerPadding ->

                    AllProductsScreen(
                        state = state.value,
                        onFavoriteButtonClicked = {
                            Log.i(TAG, "onCreate: clicked on ${it.title}")
                            viewmodel.addProductToFavorite(it)
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
