package com.example.cookbook.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookbook.ui.theme.CookbookTheme

@Composable
fun DialogInform(title: String, text: String, onCancel: () -> Unit, onOk: () -> Unit) {
    AlertDialog(
        title = {
            //Text(text = title)
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

@Preview
@Composable
private fun DialogInformPreview() {
    CookbookTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            //color = MaterialTheme.colors.background
        ) {
            DialogInform("", "DCBA\r\naaaaa\r\nbbbbb\r\nccccc", { }, { } )
        }
    }
}