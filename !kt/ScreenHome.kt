package com.example.cookbook.presentation.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
//import androidx.compose.material3.Icon
//import androidx.compose.material.Scaffold
//import androidx.compose.material.FabPosition
import androidx.compose.material3.*

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.cookbook.R

import com.example.cookbook.data.entities.Data
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.viewmodels.CookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenHome(nc: NavController?, vm: CookViewModel) {

    //---номер текущей закладки в верхней строке-----------------------------------------
    var iTab by rememberSaveable { mutableStateOf(0) }
    val onIndexChange = { value: Int -> iTab = value }

    //---список id для выбранных записей-------------------------------------------------
    var selectedId: List<Int> by rememberSaveable { mutableStateOf(listOf()) }
    selectedId = vm.getSelectedId(iTab).observeAsState(listOf()).value

    //---количество выбранных записей----------------------------------------------------
    var numerSelected by rememberSaveable { mutableStateOf(0) }
    numerSelected = vm.numerSelected(iTab).observeAsState(0).value

    var nSelTag by rememberSaveable { mutableStateOf(0) }
    nSelTag = vm.getNSelTag().observeAsState(0).value

    var nSelDish by rememberSaveable { mutableStateOf(0) }
    nSelDish = vm.getNSelDish().observeAsState(0).value

    var nSelRecipe by rememberSaveable { mutableStateOf(0) }
    nSelRecipe = vm.getNSelRecipe().observeAsState(0).value

    var nSelIngredient by rememberSaveable { mutableStateOf(0) }
    nSelIngredient = vm.getNSelIngredient().observeAsState(0).value

    var nSelMeasure by rememberSaveable { mutableStateOf(0) }
    nSelMeasure = vm.getNSelMeasure().observeAsState(0).value

    var nSelAuthor by rememberSaveable { mutableStateOf(0) }
    nSelAuthor = vm.getNSelAuthor().observeAsState(0).value

    val tabNSelected = mutableListOf(
        nSelTag,
        nSelDish,
        nSelRecipe,
        nSelIngredient,
        nSelMeasure,
        nSelAuthor
    )

    //---состояние видимости диалога удаления записи-------------------------------------
    var dialogDeleteState by rememberSaveable { mutableStateOf(false) }

    if (dialogDeleteState) {
        DialogInform(
            title = "",
            text = "удалить запись?",
            onCancel = {
                dialogDeleteState = false
            },
            onOk = {
                vm.deleteSelectedId(selectedId, iTab)
                dialogDeleteState = false
            }
        )
    }

    //---состояние видимости диалога добавления записей в контейнер----------------------
    var dialogIncludeState by rememberSaveable { mutableStateOf(false) }

    if (dialogIncludeState) {
        DialogInform(
            title = "",
            text = "добавить выбранные записи в контейнер?",
            onCancel = {
                dialogIncludeState = false
            },
            onOk = {
                //vm.addSelectedId(selectedId, iTab)
                dialogIncludeState = false
            }
        )
    }

    //---лямбда изменяющая состояние видимости диалога удаления записи-------------------
    val onSetDialogDeleteState = { value: Boolean ->
        dialogDeleteState = value
    }

    //---лямбда изменяющая состояние видимости диалога добавления записи-------------------
    val onSetDialogIncludeState = { value: Boolean ->
        dialogIncludeState = value
    }

    //Log.i("--==>", "HOME")

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
                //Header(iTab, onIndexChange)
            })
        },

        content = { innerPadding ->
            Column {
                Text(" \r\n \r\n ")
                HeaderTabs(iTab, tabNSelected, onIndexChange)
                Tabulator(vm = vm, iTab = iTab, onSetDialogIncludeState)
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
                    nc?.navigate(NavRoutes.AddData.route + "/$iTab")
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
                    tint = if (numerSelected == 1) Color.Black else Color.Gray,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                            if (numerSelected == 1) {
                                val id = selectedId[0]
                                val dataSel = vm.getDataById(id, iTab)
                                val name = (dataSel as Data).name
                                nc?.navigate(NavRoutes.EditData.route + "/$iTab/$name/$id")
                            }
                        }
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = if (numerSelected != 0) Color.Black else Color.Gray,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                            if (numerSelected != 0) onSetDialogDeleteState(true)
                        }
                )
                if (iTab == 1 || iTab == 2) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = when {
                            iTab == 1 && nSelTag != 0 && nSelDish != 0 -> Color.Black
                            iTab == 2 && nSelDish == 1 && nSelRecipe != 0 -> Color.Black
                            else -> Color.Gray
                        },

                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .clickable {
                                if (numerSelected != 0) onSetDialogIncludeState(true)
                            }
                    )
                }
                Icon(
                    //imageVector = Icons.Default.Check,
                    painter = painterResource(id = R.drawable.ic_baseline_filter_alt_24),
                    contentDescription = null,
                    tint = if (numerSelected != 0) Color.Black else Color.Gray,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
//                            if (numerSelected != 0) onSetDialogState(true)
                        }
                )
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                            vm.liveData2.setValue(999)
                        }
                )
            }
        }
    )
}








//PREVIEW UI----------------------------------------------------------------------------
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