package com.example.cookbook.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookbook.data.entities.Data
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class CookRepository(private val cookDao: CookDao) {

    val allDataTag: Flow<List<Data>>
        get() = cookDao.getDataTag()

    val allDataDish: Flow<List<Data>>
        get() = cookDao.getDataDish()

    val allDataRecipe: Flow<List<Data>>
        get() = cookDao.getDataRecipe()

    val allDataIngredient: Flow<List<Data>>
        get() = cookDao.getDataIngredient()

    val allDataMeasure: Flow<List<Data>>
        get() = cookDao.getDataMeasure()

    val allDataAuthor: Flow<List<Data>>
        get() = cookDao.getDataAuthor()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun setData(id: Int, name: String, index: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            when (index) {
                0 -> cookDao.setDataTag(id, name)
                1 -> cookDao.setDataDish(id, name)
                2 -> cookDao.setDataRecipe(id, name)
                3 -> cookDao.setDataIngredient(id, name)
                4 -> cookDao.setDataMeasure(id, name)
                else -> cookDao.setDataAuthor(id, name)
            }
        }
    }

    fun deleteSelectedId(selectedId: List<Int>, index: Int) {
        selectedId.forEach() { id ->
            coroutineScope.launch(Dispatchers.IO) {
                when (index) {
                    0 -> cookDao.deleteIdTag(id)
                    1 -> cookDao.deleteIdDish(id)
                    2 -> cookDao.deleteIdRecipe(id)
                    3 -> cookDao.deleteIdIngredient(id)
                    4 -> cookDao.deleteIdMeasure(id)
                    else -> cookDao.deleteIdAuthor(id)
                }
            }
        }
    }

    fun getSelectedId(index: Int): Flow<List<Int>> {
        return when (index) {
            0 -> cookDao.getSelectedIdTag()
            1 -> cookDao.getSelectedIdDish()
            2 -> cookDao.getSelectedIdRecipe()
            3 -> cookDao.getSelectedIdIngredient()
            4 -> cookDao.getSelectedIdMeasure()
            else -> cookDao.getSelectedIdAuthor()
        }
    }

    fun numerSelected(index: Int): Flow<Int> {
        return when (index) {
            0 -> cookDao.numerSelTag()
            1 -> cookDao.numerSelDish()
            2 -> cookDao.numerSelRecipe()
            3 -> cookDao.numerSelIngredient()
            4 -> cookDao.numerSelMeasure()
            else -> cookDao.numerSelAuthor()
        }
    }

    fun insertTag(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            cookDao.insertTag(name)
        }
    }

    fun selectedOff(id: Int, index: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            when (index) {
                0 -> cookDao.selectedOffTag(id)
                1 -> cookDao.selectedOffDish(id)
                2 -> cookDao.selectedOffRecipe(id)
                3 -> cookDao.selectedOffIngredient(id)
                4 -> cookDao.selectedOffMeasure(id)
                5 -> cookDao.selectedOffAuthor(id)
                else -> {}
            }
        }
    }

    fun selectedOn(id: Int, index: Int) {
        Log.i("--==>", "id = $id, index = $index")
        coroutineScope.launch(Dispatchers.IO) {
            when (index) {
                0 -> cookDao.selectedOnTag(id)
                1 -> cookDao.selectedOnDish(id)
                2 -> cookDao.selectedOnRecipe(id)
                3 -> cookDao.selectedOnIngredient(id)
                4 -> cookDao.selectedOnMeasure(id)
                5 -> cookDao.selectedOnAuthor(id)
                else -> {}
            }
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

    fun getNSelected(): LiveData<List<Int>> {
        val result = mutableListOf(0,0,0,0,0,0)
        val liveDataResult = MutableLiveData<List<Int>>()
        coroutineScope.launch(Dispatchers.IO) {
            result[0] = cookDao.numerSelectedTag()
            result[1] = cookDao.numerSelectedDish()
            result[2] = cookDao.numerSelectedRecipe()
            result[3] = cookDao.numerSelectedIngredient()
            result[4] = cookDao.numerSelectedMeasure()
            result[5] = cookDao.numerSelectedAuthor()
        }

        liveDataResult.value = result
        return liveDataResult
    }
    
    fun getNSelTag(): Flow<Int> = cookDao.numerSelTag()
    fun getNSelDish(): Flow<Int> = cookDao.numerSelDish()
    fun getNSelRecipe(): Flow<Int> = cookDao.numerSelRecipe()
    fun getNSelIngredient(): Flow<Int> = cookDao.numerSelIngredient()
    fun getNSelMeasure(): Flow<Int> = cookDao.numerSelMeasure()
    fun getNSelAuthor(): Flow<Int> = cookDao.numerSelAuthor()

    fun getDataById(id: Int, index: Int): Any {
        val liveDataResult: Any
        runBlocking {
            val result = coroutineScope.async(Dispatchers.IO) {
                when (index) {
                    0 -> cookDao.getDataByIdTag(id)
                    1 -> cookDao.getDataByIdDish(id)
                    2 -> cookDao.getDataByIdRecipe(id)
                    3 -> cookDao.getDataByIdIngredient(id)
                    4 -> cookDao.getDataByIdMeasure(id)
                    else -> cookDao.getDataByIdAuthor(id)
                }
            }
            liveDataResult = result.await()
        }
        return liveDataResult
    }
}