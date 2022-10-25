package com.example.cookbook.data

import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.cookbook.data.entities.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CookRepository(private val cookDao: CookDao) {

    val allDataTag: LiveData<List<Data>> = cookDao.getDataTag()
    val allDataDishes: LiveData<List<Data>> = cookDao.getDataDish()
    val allDataRecipe: LiveData<List<Data>> = cookDao.getDataRecipe()
    val allDataIngredient: LiveData<List<Data>> = cookDao.getDataIngredient()
    val allDataMeasure: LiveData<List<Data>> = cookDao.getDataMeasure()
    val allDataAuthor: LiveData<List<Data>> = cookDao.getDataAuthor()

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

    fun getSelectedId(index: Int): LiveData<List<Int>> {
        return when (index) {
            0 -> cookDao.getSelectedIdTag()
            1 -> cookDao.getSelectedIdDish()
            2 -> cookDao.getSelectedIdRecipe()
            3 -> cookDao.getSelectedIdIngredient()
            4 -> cookDao.getSelectedIdMeasure()
            else -> cookDao.getSelectedIdAuthor()
        }
    }

    fun numerOfSelected(index: Int): LiveData<Int> {
        return when (index) {
            0 -> cookDao.numerOfSelectedTag()
            1 -> cookDao.numerOfSelectedDish()
            2 -> cookDao.numerOfSelectedRecipe()
            3 -> cookDao.numerOfSelectedIngredient()
            4 -> cookDao.numerOfSelectedMeasure()
            else -> cookDao.numerOfSelectedAuthor()
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
        val liveDataResult = MutableLiveData<List<Int>>()
        val result = mutableListOf(0,0,0,0,0,0)
        coroutineScope.launch(Dispatchers.IO) {
            result[0] = cookDao.numerOfSelTag()
            result[1] = cookDao.numerOfSelDish()
            result[2] = cookDao.numerOfSelRecipe()
            result[3] = cookDao.numerOfSelIngredient()
            result[4] = cookDao.numerOfSelMeasure()
            result[5] = cookDao.numerOfSelAuthor()
        }

        liveDataResult.value = result
        return liveDataResult
    }

}