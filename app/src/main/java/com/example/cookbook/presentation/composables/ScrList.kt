package com.example.cookbook.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material3.Icon
//import androidx.compose.material.Scaffold
//import androidx.compose.material.FabPosition
import androidx.compose.material3.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.cookbook.R
import com.example.cookbook.data.entities.Data
import com.example.cookbook.viewmodels.CookViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/******************************************************************************
 * iTab - номер текущей закладки
 * selectedId - список id выбранных записей
 * numberSelected - количество выбранных записей
 * dialogDeleteState - состояние видимости диалога удаления записи
 * dialogIncludeState - состояние видимости диалога добавления записей в контейнер
 * onSetDialogDeleteState - лямбда изменяющая состояние видимости диалога удаления записи
 * onSetDialogIncludeState - лямбда изменяющая состояние видимости диалога добавления записи
 ******************************************************************************/

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScrList(
    nc: NavController?,
    vm: CookViewModel,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    //-------------режим редактирования----------------------------------------
    var modeEdit by rememberSaveable { mutableStateOf("NEW") }
    val onModeEdit = { value: String -> modeEdit = value }

    //-------------id первой из выбранных записей------------------------------
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    val onSelectedIndexChange = { value: Int -> selectedIndex = value }

    //-------------id значение поля name из выбранных записей------------------
    var selectedName  by rememberSaveable { mutableStateOf("") }
    val onSelectedNameChange = { value: String -> selectedName = value }

    //-------------номер закладки----------------------------------------------
    var iTab by rememberSaveable { mutableStateOf(0) }
    val onIndexChange = { value: Int -> iTab = value }

    var selectedId: List<Int> by rememberSaveable { mutableStateOf(listOf()) }
    selectedId = vm.getSelectedId(iTab).observeAsState(listOf()).value

    var numberSelected by rememberSaveable { mutableStateOf(0) }
    numberSelected = vm.numberSelected(iTab).observeAsState(0).value

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

    var dialogTestState by rememberSaveable { mutableStateOf(false) }
    var dialogDeleteState by rememberSaveable { mutableStateOf(false) }
    var dialogIncludeState by rememberSaveable { mutableStateOf(false) }
    var dialogEditState by rememberSaveable { mutableStateOf(false) }

    val onSetDialogTest = { value: Boolean -> dialogTestState = value }
    val onSetDialogDeleteState = { value: Boolean -> dialogDeleteState = value }
    val onSetDialogIncludeState = { value: Boolean -> dialogIncludeState = value }
    val onSetDialogEditState = { value: Boolean -> dialogEditState = value }

    //-----------------------------------------------------------------------------------
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "cook book",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo",
//                        modifier = Modifier.scale(scale.value)
                            )
                        }
                        //Header(iTab, onIndexChange)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            },

            content = { innerPadding ->
                Column {
                    Text(" \r\n \r\n ")
                    HeaderTabs(iTab, tabNSelected, onIndexChange)
                    Tabulator(vm = vm, iTab = iTab, onSetDialogIncludeState)
                }
            },

            floatingActionButtonPosition = FabPosition.End,

            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
//                        nc?.navigate(NavRoutes.AddData.route + "/$iTab")
                        onModeEdit("NEW")
                        onSelectedIndexChange(0)
                        onSelectedNameChange("")
                        onSetDialogEditState(true)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add Note Button"
                        )
                    }
                )
            },

            bottomBar = {
                BottomAppBar {
                    //----------------------кнопка РЕДАКТИРОВАТЬ
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = if (numberSelected == 1) Color.Black else Color.Gray,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .clickable {
                                if (numberSelected == 1) {
                                    val id = selectedId[0]
                                    val dataSel = vm.getDataById(id, iTab)
                                    val name = (dataSel as Data).name
                                    //nc?.navigate(NavRoutes.EditData.route + "/$iTab/$name/$id")
                                    onModeEdit("EDIT")
                                    onSelectedIndexChange(id)
                                    onSelectedNameChange(name)
                                    onSetDialogEditState(true)
                                }
                            }
                    )
                    //----------------------кнопка УДАЛИТЬ
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = if (numberSelected != 0) Color.Black else Color.Gray,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .clickable {
                                if (numberSelected != 0) onSetDialogDeleteState(true)
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
                                    if (numberSelected != 0) onSetDialogIncludeState(true)
                                }
                        )
                    }
                    //----------------------кнопка ФИЛЬТРОВАТЬ (не работает)
                    Icon(
                        //imageVector = Icons.Default.Check,
                        painter = painterResource(id = R.drawable.ic_baseline_filter_alt_24),
                        contentDescription = null,
                        tint = if (numberSelected != 0) Color.Black else Color.Gray,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .clickable {
//                            if (numerSelected != 0) onSetDialogState(true)
                            }
                    )
                    //----------------------кнопка ОК (не работает)
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .clickable {
                                //onSetDialogTest(true)
                            }
                    )
                }
            }
        )

    if (dialogTestState) {
        println("--==> ScrDialogTest 1...")
        ScrDialog("ABC", {}, {})
    }

    if (dialogDeleteState) {
        ScrDialog(
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

    if (dialogEditState) {
        ScrEdit(
            vm,
            iTab,
            selectedName,
            modeEdit,
            id = selectedIndex,
            onCancel = {
                dialogEditState = false
            },
        )
    }
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