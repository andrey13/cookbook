package com.example.cookbook

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookbook.data.Dish
import com.example.cookbook.ui.theme.CookbookTheme
import com.example.cookbook.viewmodels.CookViewModel
import com.example.cookbook.viewmodels.CookViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookbookTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val owner = LocalViewModelStoreOwner.current
                    val repo = (application as CookApplication).repository

                    owner?.let {
                        val viewModel: CookViewModel = viewModel(
                            it,
                            "CookViewModel",
                            CookViewModelFactory(repo)
                        )
                        SetupUI(viewModel)
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetupUI(vm: CookViewModel?) {

    val allDish: List<Dish> = if (vm == null) {
        listOf(Dish(1, "A"), Dish(2, "B"))
    } else {
        vm.allDish.observeAsState(listOf()).value
    }

    Scaffold(
        drawerContent = {
            Text("Drawer title", modifier = Modifier.padding(16.dp))
            Divider()
            // Drawer items
        },
        topBar = {
            TopAppBar { /* Top app bar content */ }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* ... */ }) {
            }
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar { /* Bottom app bar content */ }
        }
    ) {
        ListDish(allDish)
    }


}

@Composable
fun ListDish(allDish: List<Dish>) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        item {
            TitleRow(head1 = "id", head2 = "name")
        }

        items(allDish) { dish ->
            ProductRow(id = dish.id, name = dish.name)
        }

    }
}

@Composable
fun TitleRow(head1: String, head2: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f)
        )
        Text(
            head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f)
        )
    }
}


@Composable
fun ProductRow(id: Int, name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            id.toString(), modifier = Modifier
                .weight(0.1f)
        )
        Text(name, modifier = Modifier.weight(0.2f))
    }
}


// PREVIEW UI----------------------------------------------------------------------------
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CookbookTheme {
        CookbookTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                SetupUI(null)
            }
        }
    }
}