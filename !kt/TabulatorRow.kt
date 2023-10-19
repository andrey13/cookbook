package com.example.cookbook.presentation.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cookbook.viewmodels.CookViewModel

// строка таблицы -----------------------------------------------------------------------
@Composable
fun TabulatorRow(
    vm: CookViewModel,
    id: Int,
    iTab: Int,
    name: String,
    sel: Int = 0,
) {
    //Log.i("--==>", "TABULATOR ROW")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(if (sel == 1) Color.Yellow else Color.White)
            .clickable {
                if (sel == 0) vm.selectedOn(id, iTab) else vm.selectedOff(id, iTab)
            }
    ) {
        Text(
            id.toString(), modifier = Modifier
                .weight(0.1f)
        )
        Text(name, modifier = Modifier.weight(0.2f))
    }
}
