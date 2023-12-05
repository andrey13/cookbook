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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.cookbook.ui.theme.CookbookTheme

/******************************************************************************
 * index - индекс вкладки из которой вызвана форма
 * name - значение редактируемой строки
 * mode - режим формы ("NEW" - создание записи, "EDIT" - редактирование записи
 * id - идентификатор записи в БД
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrTest(
    nc: NavController?,
) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Settings", "Favorites")
    val icons = listOf(
        Icons.Filled.Home, Icons.Filled.Settings,
        Icons.Filled.Favorite)

    val colorPrimary = MaterialTheme.colorScheme.primary
    println("Test--==> colorPrimary = $colorPrimary")
//    CookbookTheme {
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
fun PreviewScrTest() {
    CookbookTheme {
        ScrTest(null)
    }
}