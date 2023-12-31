package com.example.cookbook.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.tabrText
import com.example.cookbook.viewmodels.CookViewModel

@Composable
fun ScreenEditData(
    nc: NavController?,
    vm: CookViewModel,
    index: Int,
    name: String,
    mode: String,
    id: Int = 0
) {
    var text by rememberSaveable { mutableStateOf(name) }

    Column {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("название ${tabrText[index]}") },
            singleLine = true
        )
        Button(onClick = {
            when(mode) {
                "NEW" -> if (text != "") {
                    when (index) {
                        0 -> vm.insertTag(text)
                        1 -> vm.insertDish(text)
                        2 -> vm.insertRecipe(text)
                        3 -> vm.insertIngredient(text)
                        4 -> vm.insertMeasure(text)
                        5 -> vm.insertAuthor(text)
                        else -> {}
                    }
                    nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
                }
                "EDIT" -> if (text != "") {
                    vm.setData(id, text, index)
                    nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
                }
                else -> {}
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
//@Preview(showBackground = true)
//@Composable
//private fun DefaultPreview1() {
//    CookbookTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colors.background
//        ) {
//            ScreenEditData(null, null, 0, "", "NEW")
//        }
//    }
//}
