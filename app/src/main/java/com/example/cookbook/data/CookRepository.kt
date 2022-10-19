package com.example.cookbook.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CookRepository(private val cookDao: CookDao) {

    val allDishes : LiveData<List<Dish>> = cookDao.getAll()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(dish: Dish) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.insert(dish)
        }
    }

    fun deleteAll() {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.deleteAll()
        }
    }

    fun deleteId(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.deleteId(id)
        }
    }


}