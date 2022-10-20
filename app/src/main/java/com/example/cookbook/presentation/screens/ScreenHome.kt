package com.example.cookbook.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookbook.data.entities.Data
import com.example.cookbook.indexTab
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.tabText
import com.example.cookbook.ui.theme.CookbookTheme
import com.example.cookbook.viewmodels.CookViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenHome(nc: NavController?, vm: CookViewModel?) {

    var index by remember { mutableStateOf(indexTab) }

    val onIndexChange = { value: Int -> index = value}

    var data : List<Data> by remember {
        mutableStateOf(listOf())
    }

    data = if (vm == null) {
        listOf(Data(1, "A"), Data(2, "B"))
    } else {
        when(index) {
            0 -> vm.allDataTag.observeAsState(listOf()).value
            1 -> vm.allDataDish.observeAsState(listOf()).value
            2 -> vm.allDataRecipe.observeAsState(listOf()).value
            3 -> vm.allDataIngredient.observeAsState(listOf()).value
            4 -> vm.allDataMeasure.observeAsState(listOf()).value
            5 -> vm.allDataAuthor.observeAsState(listOf()).value
            else -> listOf(Data(1, "A"), Data(2, "B"))
        }
    }

    Scaffold(
        drawerContent = {
            Text("Drawer title", modifier = Modifier.padding(16.dp))
            Divider()
        },
        drawerElevation = DrawerDefaults.Elevation,
        drawerShape = MaterialTheme.shapes.large,
        topBar = { TopAppBar { } },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    nc?.navigate(NavRoutes.AddDish.route + "/$index")
                }
            ) {}
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar { /* Bottom app bar content */ }
        }
    ) {
        Column {
            ListTables(index, onIndexChange)
            Tabulator(data = data, index)
        }
    }
}

@Composable
fun ListTables(index: Int, onIndexChange: (Int) -> Unit) {
    val selectedIndex = remember { mutableStateOf(index) }

    ScrollableTabRow(
        selectedTabIndex = selectedIndex.value,
        divider = {},
        edgePadding = 2.dp,
        indicator = noIndicator,
        backgroundColor = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        tabText.forEachIndexed { index, text ->
            Tab(
                selected = index == selectedIndex.value,
                onClick = {
                    if (selectedIndex.value != index) {
                        selectedIndex.value = index
                        indexTab = index
                        onIndexChange(index)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            ) {
                CustomImageChip(
                    text = text,
                    selected = index == selectedIndex.value,
                    modifier = Modifier
                        .width(120.dp)
                        .padding(start = 2.dp, end = 2.dp, top = 4.dp, bottom = 0.dp)
                )
            }
        }
    }
}

@Composable
private fun CustomImageChip(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = when {
            selected -> MaterialTheme.colorScheme.primary
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colorScheme.onPrimary
            else -> Color.Black
        },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colorScheme.primary
                else -> Color.Black
            }
        ),
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp),
            textAlign = TextAlign.Center,
        )
    }
}

private val noIndicator: @Composable (List<TabPosition>) -> Unit = {}

@Composable
fun Tabulator(data: List<Data>, index: Int) {
    LazyColumn(
        Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        item() {
            TitleRow(head1 = "id", head2 = tabText[index])
        }

        items(data) { item ->
            DataRow(id = item.id, name = item.name)
        }

    }
}

@Composable
fun TitleRow(head1: String, head2: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(5.dp),
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
fun DataRow(id: Int, name: String) {
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
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            ScreenHome(null, null)
        }
    }
}