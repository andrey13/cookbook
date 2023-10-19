package com.example.cookbook.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookbook.*
import com.example.cookbook.presentation.composables.ScreenMain
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

                Log.i("--==>", "MainActivity: resources = $resources")
                tabText = resources.getStringArray(R.array.tab_array)
                tabsText = resources.getStringArray(R.array.tabs_array)
                tabrText = resources.getStringArray(R.array.tabr_array)
                tabnText = resources.getStringArray(R.array.tabn_array)


                owner?.let {
                    val viewModel: CookViewModel = viewModel(
                        it,
                        "CookViewModel",
                        CookViewModelFactory(repo)
                    )

                    viewModel.liveData1.observe(this) { value ->
                        Log.i("--==>", "LiveDate1 Observe <--- $value")
                    }

                    viewModel.liveData1.observe(this) { value ->
                        Log.i("--==>", "LiveDate2 Observe <--- $value")
                    }

                    viewModel.liveData1.observe(this) { value ->
                        Log.i("--==>", "LiveData3 Observe <--- $value")
                    }

//                    viewModel.liveData3.observe(this) { value ->
//                        Log.i("--==>", "LiveData3 Observe <--- $value")
//                    }



                    ScreenMain(viewModel)
                }
            }
        }
    }
}

