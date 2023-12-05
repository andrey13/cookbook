package com.example.cookbook.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.tabrText
import com.example.cookbook.ui.theme.CookbookTheme
import com.example.cookbook.viewmodels.CookViewModel

/******************************************************************************
 * index - индекс вкладки из которой вызвана форма
 * name - значение редактируемой строки
 * mode - режим формы ("NEW" - создание записи, "EDIT" - редактирование записи
 * id - идентификатор записи в БД
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrEditData1(
    nc: NavController?,
//    vm: CookViewModel,
    index: Int,
    name: String,
    mode: String,
    id: Int = 0
) {
    var text by rememberSaveable { mutableStateOf(name) }

    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Settings", "Favorites")
    val icons = listOf(
        Icons.Filled.Home, Icons.Filled.Settings,
        Icons.Filled.Favorite)

//    CookbookTheme {
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
//            label = { Text("название ${tabrText[index]}") },
                singleLine = true
            )

            //-----------------------------------OK
            Button(
                onClick = {},
//                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Text("OK")
            }

            //-----------------------------------CANCEL
            Button(onClick = {
//            nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
            }) {
                Text("Cancel")
            }
        }



        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(title = { Text("ThemeDemo") })
            Button(onClick = { }) {
                Text("MD3 Button")
            }
            Text("A Theme Demo")
            FloatingActionButton(onClick = { }) {
                Text("FAB")
            }
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = null) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }



//    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScrEditData1() {
//    val vm: CookViewModel = viewModel()
    CookbookTheme {
        ScrEditData1(
            null,
//        vm,
            1,
            "ABC",
            "EDIT"

        )
    }
}