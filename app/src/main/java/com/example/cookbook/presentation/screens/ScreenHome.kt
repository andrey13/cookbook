package com.example.cookbook.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
//import androidx.compose.material.Scaffold
//import androidx.compose.material.FabPosition
import androidx.compose.material3.*

import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.text.style.TextOverflow

import androidx.navigation.NavController

import com.example.cookbook.data.entities.Data
import com.example.cookbook.indexTab
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.tabNSel
import com.example.cookbook.tabText
import com.example.cookbook.viewmodels.CookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenHome(nc: NavController?, vm: CookViewModel) {

    //---номер текущей закладки в верхней строке-----------------------------------------
    var index by remember { mutableStateOf(indexTab) }
    val onIndexChange = { value: Int -> index = value }

    //---список id для выбранных записей-------------------------------------------------
    var selectedId: List<Int> by remember { mutableStateOf(listOf()) }
    selectedId = vm.getSelectedId(index).observeAsState(listOf()).value

    //---количество выбранных записей----------------------------------------------------
    var numerOfSelected by remember { mutableStateOf(0) }
    numerOfSelected = vm.numerOfSelected(index).observeAsState(0).value

    var tabNSelected by remember { mutableStateOf(listOf(0, 0, 0, 0, 0, 0)) }
    tabNSelected = vm.getNSelected().observeAsState(listOf(0, 0, 0, 0, 0, 0)).value

    //Log.i("--==>", "tabNSelected = $tabNSelected")
    //Log.i("--==>", "ScreenHome")

    //---состояние видимости диалога удаления записи-------------------------------------
    var dialogDeleteState by remember { mutableStateOf(false) }

    if (dialogDeleteState) {
        DialogDelete(
            text = "",
            onCancel = {
                dialogDeleteState = false
            },
            onOk = {
                vm.deleteSelectedId(selectedId, index)
                dialogDeleteState = false
            }
        )
    }

    //---лямбда изменяющая состояние видимости диалога удаления записи-------------------
    val onSetDialogState = { value: Boolean ->
        dialogDeleteState = value
    }

    //---данные для заполнения таблицы---------------------------------------------------
    var data: List<Data> by remember {
        mutableStateOf(listOf())
    }

    data = when (index) {
        0 -> vm.allDataTag.observeAsState(listOf()).value
        1 -> vm.allDataDish.observeAsState(listOf()).value
        2 -> vm.allDataRecipe.observeAsState(listOf()).value
        3 -> vm.allDataIngredient.observeAsState(listOf()).value
        4 -> vm.allDataMeasure.observeAsState(listOf()).value
        5 -> vm.allDataAuthor.observeAsState(listOf()).value
        else -> listOf(Data(1, "Invalid Tab", 0))
    }


    //-----------------------------------------------------------------------------------
    Scaffold(
        //---верхняя строка--------------------------------------------------------------
        topBar = {
            TopAppBar(title = {
                Text(
                    "cook book",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                //Header(index, onIndexChange)
            })
        },

        content = { innerPadding ->
            Column {
                Text(" \r\n \r\n ")
                Header(index, tabNSelected, onIndexChange)
                Tabulator(vm = vm, data = data, index, onSetDialogState)
            }
        },
        //---левая шторка----------------------------------------------------------------
//        drawerContent = {
//            Text("Drawer title", modifier = Modifier.padding(16.dp))
//            Divider()
//        },
//        drawerElevation = DrawerDefaults.Elevation,
//        drawerShape = MaterialTheme.shapes.large,

        //---плавающая кнопка добавления записи------------------------------------------
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    nc?.navigate(NavRoutes.AddData.route + "/$index")
                },
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Note Button"
                    )
                }
            )
        },
        //isFloatingActionButtonDocked = true,

        //---нижняя строка---------------------------------------------------------------
        bottomBar = {
            BottomAppBar {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = if (numerOfSelected == 1) Color.White else Color.Gray,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                            val id = selectedId[0]
                            val textinit = data[data.indexOf(Data(id, "", 1))]
                            nc?.navigate(NavRoutes.EditData.route + "/$index/$textinit/$id")
                        }
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = if (numerOfSelected == 0) Color.Gray else Color.White,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                            if (numerOfSelected != 0) onSetDialogState(true)
                        }
                )
            }
        }
    )
}

// строка с закладками ------------------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    index: Int,
    tabNSelected: List<Int>,
    onIndexChange: (Int) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(index) }

    ScrollableTabRow(
        selectedTabIndex = selectedIndex.value,
        divider = {},
        edgePadding = 2.dp,
        indicator = noIndicator,
        //backgroundColor = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .background(Color.Transparent)
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
                    .padding(1.dp)
            ) {
                BadgedBox(
                    badge = {
                        if (tabNSelected[index] != 0) {
                            Badge(
                                modifier = Modifier.offset(-20.dp, 11.dp)
                            ) {
                                Text(
                                    tabNSelected[index].toString(),
                                )
                            }
                        }
                    }
                ) {
                    HeaderTab(
                        text = text,
                        selected = index == selectedIndex.value,
                        modifier = Modifier
                            .width(120.dp)
                            .padding(start = 2.dp, end = 2.dp, top = 4.dp, bottom = 4.dp)
                    )
                }

            }
        }
    }
}

private val noIndicator: @Composable (List<TabPosition>) -> Unit = {}

// закладка -----------------------------------------------------------------------------
@Composable
private fun HeaderTab(
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
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
            textAlign = TextAlign.Center,
        )
    }
}

// шапка таблицы ------------------------------------------------------------------------
@Composable
fun TitleRow(head1: String, head2: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(2.dp),
    ) {
        Text(
            head1, color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(0.1f)
        )
        Text(
            head2, color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(0.2f)
        )
    }
}

// таблица ------------------------------------------------------------------------------
@Composable
fun Tabulator(
    vm: CookViewModel,
    data: List<Data>,
    index: Int,
    onSetDialogState: (b: Boolean) -> Unit
) {
    TitleRow(head1 = "id", head2 = tabText[index])
    LazyColumn(
        Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(0.dp)
    ) {

        itemsIndexed(
            items = data,
            itemContent = { idx, item ->
                DataRow(vm, item.id, index, item.name, item.selected)
            }
        )
    }
}

// строка таблицы -----------------------------------------------------------------------
@Composable
fun DataRow(
    vm: CookViewModel,
    id: Int,
    index: Int,
    name: String,
    sel: Int = 0,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(if (sel == 1) Color.Yellow else Color.White)
            .clickable {
                if (sel == 0) vm.selectedOn(id, index) else vm.selectedOff(id, index)
            }
    ) {
        Text(
            id.toString(), modifier = Modifier
                .weight(0.1f)
        )
        Text(name, modifier = Modifier.weight(0.2f))
    }
}


// PREVIEW UI----------------------------------------------------------------------------
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CookbookTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.surface
//        ) {
//            ScreenHome(null, null)
//        }
//    }
//}