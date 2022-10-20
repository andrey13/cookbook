package com.example.cookbook.data

import androidx.lifecycle.LiveData
import com.example.cookbook.data.entities.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CookRepository(private val cookDao: CookDao) {

    val allDataTag : LiveData<List<Data>> = cookDao.getDataTag()
    val allDataDishes : LiveData<List<Data>> = cookDao.getDataDish()
    val allDataReceipe : LiveData<List<Data>> = cookDao.getDataReceipe()
    val allDataIngredient : LiveData<List<Data>> = cookDao.getDataIngredient()
    val allDataMeasure : LiveData<List<Data>> = cookDao.getDataMeasure()
    val allDataAutor : LiveData<List<Data>> = cookDao.getDataAutor()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertTag(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.insertTag(name)
        }
    }

    fun insertDish(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.insertDish(name)
        }
    }

    fun insertReceipe(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.insertReceipe(name)
        }
    }

    fun insertIngredient(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.insertIngredient(name)
        }
    }

    fun insertMeasure(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.insertMeasure(name)
        }
    }

    fun insertAutor(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.insertAutor(name)
        }
    }

    fun deleteAllDish() {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.deleteAllDish()
        }
    }

    fun deleteIdDish(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.deleteIdDish(id)
        }
    }


}