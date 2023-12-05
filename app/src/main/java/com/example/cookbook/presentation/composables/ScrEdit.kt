package com.example.cookbook.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.tabrText
import com.example.cookbook.ui.theme.CookbookTheme
import com.example.cookbook.viewmodels.CookViewModel

@Composable
fun ScrEdit(
//    nc: NavController?,
    vm: CookViewModel?,
    index: Int,
    name: String,
    mode: String,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
    id: Int = 0,
    label: String = tabrText[index],
) {
    var text by rememberSaveable { mutableStateOf(name) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(onClick = { })
    ) {
        val configuration = LocalConfiguration.current
        val cardWidth = minOf(300f, (configuration.screenWidthDp.dp / 1.3f).value)
        val cardHeight = minOf(300f, (configuration.screenHeightDp.dp / 4).value)

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
                    .padding(10.dp)
//                    .align(Alignment.End)
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("название $label") },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround,
//                    verticalAlignment = Alignment.Bottom
                ) {
                    //-----------------------------------OK
                    Button(
                        onClick = {
                            when (mode) {
                                "NEW" -> if (text != "") {
                                    when (index) {
                                        0 -> vm?.insertTag(text)
                                        1 -> vm?.insertDish(text)
                                        2 -> vm?.insertRecipe(text)
                                        3 -> vm?.insertIngredient(text)
                                        4 -> vm?.insertMeasure(text)
                                        5 -> vm?.insertAuthor(text)
                                        else -> {}
                                    }
//                                    nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
                                }

                                "EDIT" -> if (text != "") {
                                    vm?.setData(id, text, index)
//                                    nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
                                }

                                else -> {}
                            }
                        },
                        modifier = Modifier
                            .width(100.dp)

                    ) {
                        Text("OK")
                    }

                    //-----------------------------------CANCEL
                    Button(
//                        onClick = {
//                            nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
//                        },
                        onClick = onCancel,
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScrEdit() {
    CookbookTheme {
        ScrEdit(
//            null,
            null,
            1,
            "ABC",
            "EDIT",
            onCancel = {},
            id = 0,
            label = "test"
        )
    }
}