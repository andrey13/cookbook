package com.example.cookbook.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookbook.presentation.NavRoutes
import com.example.cookbook.tabrText
import com.example.cookbook.ui.theme.CookbookTheme
import com.example.cookbook.viewmodels.CookViewModel

/******************************************************************************
 * index - индекс вкладки из которой вызвана форма
 * name - значение редактируемой строки
 * mode - режим формы ("NEW" - создание записи, "EDIT" - редактирование записи
 * id - идентификатор записи в БД
 */

@Composable
fun ScrEditData(
    nc: NavController?,
    vm: CookViewModel?,
    index: Int,
    name: String,
    mode: String,
    modifier: Modifier = Modifier,
    id: Int = 0,
    label: String = tabrText[index],
) {
    var text by rememberSaveable { mutableStateOf(name) }

    CookbookTheme {
        ElevatedCard(
            modifier = Modifier
                .size(width = 240.dp, height = 400.dp)
                .wrapContentWidth()
                .wrapContentHeight(),
//        shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {

            Column(
                modifier = modifier
                    .padding(all = 10.dp)
                    .height(300.dp)
                    .width(300.dp)
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("название $label") },
                    singleLine = true,
                    modifier = modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
//                Button({}) { Text("AAA")}
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
                                    nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
                                }

                                "EDIT" -> if (text != "") {
                                    vm?.setData(id, text, index)
                                    nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
                                }

                                else -> {}
                            }
                        },
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("OK")
                    }

                    //-----------------------------------CANCEL
                    Button(
                        onClick = {
                            nc?.popBackStack(NavRoutes.Home.route, inclusive = false)
                        },
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
fun PreviewScrEditData() {
    ScrEditData(
        null,
        null,
        1,
        "ABC",
        "EDIT",
        id = 0,
        label = "test"

    )
}