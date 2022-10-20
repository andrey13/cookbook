package com.example.cookbook.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookbook.CookApplication
import com.example.cookbook.presentation.screens.ScreenMain
import com.example.cookbook.ui.theme.CookbookTheme
import com.example.cookbook.viewmodels.CookViewModel
import com.example.cookbook.viewmodels.CookViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookbookTheme {
                    val owner = LocalViewModelStoreOwner.current
                    val repo = (application as CookApplication).repository

                    owner?.let {
                        val viewModel: CookViewModel = viewModel(
                            it,
                            "CookViewModel",
                            CookViewModelFactory(repo)
                        )
                         ScreenMain(viewModel)
                    }
            }
        }
    }
}

