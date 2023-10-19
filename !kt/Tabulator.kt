package com.example.cookbook.presentation.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cookbook.data.entities.Data
import com.example.cookbook.tabText
import com.example.cookbook.viewmodels.CookViewModel

// таблица ------------------------------------------------------------------------------
@Composable
fun Tabulator(
    vm: CookViewModel,
    //data: List<Data>,
    iTab: Int,
    onSetDialogState: (b: Boolean) -> Unit
) {
    //---данные для заполнения таблицы---------------------------------------------------
    var data: List<Data> by rememberSaveable {
        mutableStateOf(listOf())
    }

    data = when (iTab) {
        0 -> vm.allDataTag.observeAsState(listOf()).value
        1 -> vm.allDataDish.observeAsState(listOf()).value
        2 -> vm.allDataRecipe.observeAsState(listOf()).value
        3 -> vm.allDataIngredient.observeAsState(listOf()).value
        4 -> vm.allDataMeasure.observeAsState(listOf()).value
        5 -> vm.allDataAuthor.observeAsState(listOf()).value
        else -> listOf(Data(1, "Invalid Tab", 0))
    }

    //Log.i("--==>", "TABULATOR")

    TabulatorHeader(head1 = "id", head2 = tabText[iTab])
    LazyColumn(
        Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(0.dp)
    ) {

        itemsIndexed(
            items = data,
            itemContent = { idx, item ->
                TabulatorRow(vm, item.id, iTab, item.name, item.selected)
            }
        )
    }
}

