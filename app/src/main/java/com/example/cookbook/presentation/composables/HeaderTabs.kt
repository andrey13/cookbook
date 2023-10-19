package com.example.cookbook.presentation.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.example.cookbook.R

private val noIndicator: @Composable (List<TabPosition>) -> Unit = {}


// строка с закладками ------------------------------------------------------------------
@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderTabs(
    iTab: Int,
    tabNSelected: List<Int>,
    onIndexChange: (Int) -> Unit
) {
    val selectedIndex = rememberSaveable { mutableStateOf(iTab) }

    val tabText = stringArrayResource(R.array.tab_array)

    //Log.i("--==>", "HEADER TABS")

    ScrollableTabRow(
        selectedTabIndex = selectedIndex.value,
        divider = {},
        edgePadding = 2.dp,
        indicator = noIndicator,
        //backgroundColor = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .background(Color.Transparent)
    ) {
        tabText.forEachIndexed { iTab, text ->
            Tab(
                selected = iTab == selectedIndex.value,
                onClick = {
                    if (selectedIndex.value != iTab) {
                        selectedIndex.value = iTab
                        //iTabTab = iTab
                        onIndexChange(iTab)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
            ) {
                BadgedBox(
                    badge = {
                        if (tabNSelected[iTab] != 0) {
                            Badge(
                                modifier = Modifier.offset(-20.dp, 11.dp)
                            ) {
                                Text(
                                    tabNSelected[iTab].toString(),
                                )
                            }
                        }
                    }
                ) {
                    HeaderTab(
                        text = text,
                        selected = iTab == selectedIndex.value,
                        modifier = Modifier
                            .width(120.dp)
                            .padding(start = 2.dp, end = 2.dp, top = 4.dp, bottom = 4.dp)
                    )
                }

            }
        }
    }
}
