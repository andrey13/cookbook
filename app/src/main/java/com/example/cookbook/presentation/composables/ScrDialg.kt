package com.example.cookbook.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookbook.ui.theme.CookbookTheme

@Composable
fun ScrDialog(
    text: String,
    onCancel: () -> Unit,
    onOk: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(onClick = { })
    ) {
        val configuration = LocalConfiguration.current
        val cardWidth = minOf(300f, (configuration.screenWidthDp.dp / 1.3f).value)
        val cardHeight = minOf(150f, (configuration.screenHeightDp.dp / 4).value)

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .align(Alignment.Center)
                .width(cardWidth.dp)
                .height(cardHeight.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(20.dp)
                    .align(Alignment.End)
            ) {
                Text(text)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        onClick = onOk,
                        modifier = Modifier
                            .padding(4.dp)
                            .width(100.dp)
                    ) {
                        Text(text = "Ok")
                    }
                    Button(
                        onClick = onCancel,
                        modifier = Modifier
                            .padding(4.dp)
                            .width(100.dp)
                    ) {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScrDialog() {
    CookbookTheme {
        ScrDialog(
            "ABC",
            {},
            {}
        )
    }

}