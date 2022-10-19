package com.example.cookbook

import android.app.Application
import android.util.Log
import com.example.cookbook.data.CookDataBase
import com.example.cookbook.data.CookRepository

class CookApplication : Application() {
    val database by lazy { CookDataBase.getDatabase(this) }
    val repository by lazy { CookRepository(database.cookDao()) }

    init {
        Log.i("--==>", "app1")
    }
}