package com.example.cookbook.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookbook.data.entities.Data
import com.example.cookbook.indexTab
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.tabText
import com.example.cookbook.viewmodels.CookViewModel
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenHome(nc: NavController?, vm: CookViewModel) {

    //---номер текущей закладки в верхней строке-----------------------------------------
    var index by remember { mutableStateOf(indexTab) }
    val onIndexChange = { value: Int -> index = value }

    //---список id для выбранных записей-------------------------------------------------
    var selectedIdTag: List<Int> by remember { mutableStateOf(listOf()) }
    selectedIdTag = vm.getSelectedId(index).observeAsState(listOf()).value

    //---количество выбранных записей----------------------------------------------------
    var numerOfSelected by remember { mutableStateOf(0) }
    numerOfSelected = vm.numerOfSelected(index).observeAsState(0).value

    //---состояние видимости диалога удаления записи-------------------------------------
    var dialogDeleteState by remember { mutableStateOf(false) }

    if (dialogDeleteState) {
        DialogDelete("") { dialogDeleteState = false }
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

    //---------------------------------------------------------------
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
                },
                contentColor = MaterialTheme.colorScheme.background,
                backgroundColor = MaterialTheme.colorScheme.primary,
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Note Button"
                    )
                }
            )
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = if (numerOfSelected == 1) Color.White else Color.Gray,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                        }
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = if (numerOfSelected == 0) Color.Gray else Color.White,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                            onSetDialogState(true)
                        }
                )
            }
        }
    ) {
        Column {
            ListTabs(index, onIndexChange)
            Tabulator(vm = vm, data = data, index, onSetDialogState)
        }
    }
}

// строка с закладками ------------------------------------------------------------------
@Composable
fun ListTabs(
    index: Int,
    onIndexChange: (Int) -> Unit
) {
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
                CustomTab(
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

private val noIndicator: @Composable (List<TabPosition>) -> Unit = {}

// закладка -----------------------------------------------------------------------------
@Composable
private fun CustomTab(
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
                //SwipeableListItem(index, item, onSetDialogState) { index ->  }
            }
        )
    }
}

// строка таблицы без свайпа ------------------------------------------------------------
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

// строка таблицы со свайпом ------------------------------------------------------------
@Composable
fun SwipeableListItem(
    index: Int,
    item: Data,
    onSetDialogState: (b: Boolean) -> Unit,
    onItemSwiped: (Int) -> Unit
) {
    val visible = remember(item.id) { mutableStateOf(true) }

    AnimatedVisibility(visible = visible.value) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {

            BackgroundListItem(modifier = Modifier.align(Alignment.CenterEnd), onSetDialogState)

            ForegroundListItem(item, index) {
                Log.i("--==>", "SWIPED")
                //visible.value = false
                onItemSwiped.invoke(index)
            }
        }
    }
}

enum class SwipeState {
    SWIPED, VISIBLE, MIDDLE
}

// передняя строка ----------------------------------------------------------------------
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ForegroundListItem(item: Data, index: Int, onItemSwiped: (Int) -> Unit) {
    val swipeableState = rememberSwipeableState(
        initialValue = SwipeState.VISIBLE,
        confirmStateChange = {
            if (it == SwipeState.SWIPED) {
                onItemSwiped.invoke(index)
            }
            true
        }
    )
    val swipeAnchors = mapOf(
        0f to SwipeState.VISIBLE,
        -1000f to SwipeState.SWIPED,
        -500f to SwipeState.MIDDLE
    )

    Row(
        modifier = Modifier
            .swipeable(
                state = swipeableState,
                anchors = swipeAnchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = item.name,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f),
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.padding(4.dp)
        )
    }
}

// задняя строка ------------------------------------------------------------------------
@Composable
fun BackgroundListItem(modifier: Modifier, onSetDialogState: (b: Boolean) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier
            .padding(0.dp)
            .height(33.dp)
    )
    {
        IconButton(
            onClick = {
                Log.i("--==>", "onClick Edit")
                onSetDialogState(true)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "EDIT"
            )
        }
        IconButton(onClick = { onSetDialogState(true) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "DELETE"
            )
        }
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