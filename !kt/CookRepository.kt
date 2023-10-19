package com.example.cookbook.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.cookbook.data.entities.Data
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class CookRepository(private val cookDao: CookDao) {

    //---------------------------------------------------------------------
    val liveData1: LiveData<Int> = liveData {
        val threadName = Thread.currentThread().name
        var i = 0
        while(i<10) {
            i++
            Log.i("--==>", "liveData1 emit ---> $i $threadName")
            emit(i)
        }
    }
    //---------------------------------------------------------------------
    val liveData2: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    //---------------------------------------------------------------------
    val liveData3: LiveData<Int> = liveData {
        emitSource(liveData1)
    }


    //---------------------------------------------------------------------
    fun getDataTag(): Flow<List<Data>> = cookDao.getDataTag()
    fun getDataDish(): Flow<List<Data>> = cookDao.getDataDish()
    fun getDataRecipe(): Flow<List<Data>> = cookDao.getDataRecipe()
    fun getDataIngredient(): Flow<List<Data>> = cookDao.getDataIngredient()
    fun getDataMeasure(): Flow<List<Data>> = cookDao.getDataMeasure()
    fun getDataAuthor(): Flow<List<Data>> = cookDao.getDataAuthor()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun setData(id: Int, name: String, iTab: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            when (iTab) {
                0 -> cookDao.setDataTag(id, name)
                1 -> cookDao.setDataDish(id, name)
                2 -> cookDao.setDataRecipe(id, name)
                3 -> cookDao.setDataIngredient(id, name)
                4 -> cookDao.setDataMeasure(id, name)
                else -> cookDao.setDataAuthor(id, name)
            }
        }
    }

    fun deleteSelectedId(selectedId: List<Int>, iTab: Int) {
        selectedId.forEach() { id ->
            coroutineScope.launch(Dispatchers.IO) {
                when (iTab) {
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

    fun getSelectedId(iTab: Int): Flow<List<Int>> {
        return when (iTab) {
            0 -> cookDao.getSelectedIdTag()
            1 -> cookDao.getSelectedIdDish()
            2 -> cookDao.getSelectedIdRecipe()
            3 -> cookDao.getSelectedIdIngredient()
            4 -> cookDao.getSelectedIdMeasure()
            else -> cookDao.getSelectedIdAuthor()
        }
    }

    fun numerSelected(iTab: Int): Flow<Int> {
        return when (iTab) {
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

    fun selectedOff(id: Int, iTab: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            when (iTab) {
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

    fun selectedOn(id: Int, iTab: Int) {
        Log.i("--==>", "id = $id, iTab = $iTab")
        coroutineScope.launch(Dispatchers.IO) {
            when (iTab) {
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

    fun getDataById(id: Int, iTab: Int): Any {
        val liveDataResult: Any
        runBlocking {
            val result = coroutineScope.async(Dispatchers.IO) {
                when (iTab) {
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