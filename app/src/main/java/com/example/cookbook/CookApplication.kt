package com.example.cookbook

import android.app.Application
import android.util.Log
import com.example.cookbook.data.CookDataBase
import com.example.cookbook.data.CookRepository

val tabText = listOf("Разделы", "Блюда", "Рецепты", "Ингредиенты", "Меры", "Авторы")
var indexTab = 0

class CookApplication : Application() {
    val database by lazy { CookDataBase.getDatabase(this) }
    val repository by lazy { CookRepository(database.cookDao()) }

    init {
        Log.i("--==>", "app1")
    }
}