package com.example.cookbook.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookbook.ui.theme.CookbookTheme

@Composable
fun DialogInform(
    title: String,
    text: String,
    onCancel: () -> Unit,
    onOk: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
            .wrapContentWidth()
            .wrapContentHeight(),
//        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
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
            onDismissRequest = {},
            shape = RoundedCornerShape(8.dp),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DialogInformPreview() {
    CookbookTheme {
        Surface(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DialogInform("", "DCBA\r\naaaaa\r\nbbbbb\r\nccccc", { }, { } )
        }
    }
}