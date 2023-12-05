package com.example.cookbook.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cookbook.ui.theme.CookbookTheme
import com.example.cookbook.R
import com.example.cookbook.tabText
import com.example.cookbook.tabnText
import com.example.cookbook.tabrText
import com.example.cookbook.tabsText
import dagger.hilt.android.AndroidEntryPoint

///////////////////////////////////////////////////////////////////////////////
// tabText  - заголовки вкладок в именительном падеже
// tabsText - заголовки вкладок в именительном падеже во множественном числе
// tabrText - заголовки вкладок в родительном падеже
// tabnText - заголовки вкладок в родительном падеже во множественном числе
///////////////////////////////////////////////////////////////////////////////

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tabText = resources.getStringArray(R.array.tab_array)
        tabsText = resources.getStringArray(R.array.tabs_array)
        tabrText = resources.getStringArray(R.array.tabr_array)
        tabnText = resources.getStringArray(R.array.tabn_array)

        setContent {
            CookbookTheme {
                NavGraph()
            }
        }
    }
}

