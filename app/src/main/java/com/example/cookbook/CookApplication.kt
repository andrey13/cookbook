package com.example.cookbook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

///////////////////////////////////////////////////////////////////////////////
// tabText  - заголовки вкладок в именительном падеже
// tabsText - заголовки вкладок в именительном падеже во множественном числе
// tabrText - заголовки вкладок в родительном падеже
// tabnText - заголовки вкладок в родительном падеже во множественном числе
///////////////////////////////////////////////////////////////////////////////

lateinit var tabText: Array<String>
lateinit var tabsText: Array<String>
lateinit var tabrText: Array<String>
lateinit var tabnText: Array<String>

@HiltAndroidApp
class CookApplication : Application()