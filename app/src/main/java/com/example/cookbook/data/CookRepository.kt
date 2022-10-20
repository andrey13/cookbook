package com.example.cookbook.data

import androidx.lifecycle.LiveData
import com.example.cookbook.data.entities.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CookRepository(private val cookDao: CookDao) {

    val allDataTag : LiveData<List<Data>> = cookDao.getDataTag()
    val allDataDishes : LiveData<List<Data>> = cookDao.getDataDish()
    val allDataRecipe : LiveData<List<Data>> = cookDao.getDataRecipe()
    val allDataIngredient : LiveData<List<Data>> = cookDao.getDataIngredient()
    val allDataMeasure : LiveData<List<Data>> = cookDao.getDataMeasure()
    val allDataAuthor : LiveData<List<Data>> = cookDao.getDataAuthor()

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

    fun insertRecipe(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.insertRecipe(name)
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

    fun insertAuthor(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.insertAuthor(name)
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