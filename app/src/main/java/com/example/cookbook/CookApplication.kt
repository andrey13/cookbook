package com.example.cookbook

import android.app.Application
import android.util.Log
import com.example.cookbook.data.CookDataBase
import com.example.cookbook.data.CookRepository

lateinit var tabText: Array<String>
lateinit var tabsText: Array<String>
lateinit var tabrText: Array<String>
lateinit var tabnText: Array<String>

var indexTab = 0

class CookApplication : Application() {
    private val database by lazy { CookDataBase.getDatabase(this) }
    val repository by lazy { CookRepository(database.cookDao()) }

    init {


    }
}