package com.example.cookbook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

lateinit var tabText: Array<String>
lateinit var tabsText: Array<String>
lateinit var tabrText: Array<String>
lateinit var tabnText: Array<String>

@HiltAndroidApp
class CookApplication : Application() {

}