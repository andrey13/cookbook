package com.example.cookbook.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogDelete(text: String, onDismiss: () -> Unit) {
    AlertDialog(
        title = {
            Text(text = "Удалить запись?")
        },
        text = {
            Text(text = text)
        },
        confirmButton = {
            OutlinedButton(
                onClick = onDismiss,
                modifier = Modifier
                    .padding(8.dp)
                    .width(100.dp)
            ) {
                Text(text = "Cancel")
            }
            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .padding(8.dp)
                    .width(100.dp)
            ) {
                Text(text = "Ok")
            }
        },
        onDismissRequest = onDismiss
    )
}