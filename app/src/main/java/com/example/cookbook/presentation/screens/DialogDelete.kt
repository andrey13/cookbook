package com.example.cookbook.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogDelete(text: String, onCancel: () -> Unit, onOk: () -> Unit) {
    AlertDialog(
        title = {
            Text(text = "Удалить запись?")
        },
        text = {
            Text(text = text)
        },
        confirmButton = {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier
                    .padding(4.dp)
                    .width(100.dp)
            ) {
                Text(text = "Cancel")
            }
            Button(
                onClick = onOk,
                modifier = Modifier
                    .padding(4.dp)
                    .width(100.dp)
            ) {
                Text(text = "Ok")
            }
        },
        onDismissRequest = {}
    )
}