package com.example.cookbook.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cookbook.ui.theme.CookbookTheme
import com.example.cookbook.R
import com.example.cookbook.tabText
import com.example.cookbook.tabnText
import com.example.cookbook.tabrText
import com.example.cookbook.tabsText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            CookbookTheme {
                tabText = resources.getStringArray(R.array.tab_array)
                tabsText = resources.getStringArray(R.array.tabs_array)
                tabrText = resources.getStringArray(R.array.tabr_array)
                tabnText = resources.getStringArray(R.array.tabn_array)
                NavGraph()
            }
        }
    }
}

