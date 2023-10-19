package com.example.cookbook.presentation.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// шапка таблицы ------------------------------------------------------------------------
@Composable
fun TabulatorHeader(head1: String, head2: String) {
    //Log.i("--==>", "TABULATOR HEADER")

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
