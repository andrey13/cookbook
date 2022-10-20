package com.example.cookbook.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.tabText
import com.example.cookbook.ui.theme.CookbookTheme
import com.example.cookbook.viewmodels.CookViewModel

@Composable
fun ScreenAddDish(nc: NavController?, vm: CookViewModel?, index: Int) {
    var text by rememberSaveable { mutableStateOf("") }
    Column() {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("${tabText[index]}: название") },
            singleLine = true
        )
        Button(onClick = {
            if (text != "") {
                when(index) {
                    0 -> vm?.insertTag(text)
                    1 -> vm?.insertDish(text)
                    2 -> vm?.insertReceipe(text)
                    3 -> vm?.insertIngredient(text)
                    4 -> vm?.insertaMeasure(text)
                    5 -> vm?.insertAutor(text)
                    else -> {}
                }

                nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
            }
        }) {
            Text("OK")
        }
        Button(onClick = {
            nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
        }) {
            Text("Cancel")
        }
    }
}

// PREVIEW UI----------------------------------------------------------------------------
@Preview(showBackground = true)
@Composable
private fun DefaultPreview1() {
    CookbookTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            ScreenAddDish(null, null, 0)
        }
    }
}
