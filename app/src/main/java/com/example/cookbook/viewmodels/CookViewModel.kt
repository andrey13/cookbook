package com.example.cookbook.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.cookbook.data.CookRepository
import com.example.cookbook.data.entities.Data

class CookViewModel(private val repository: CookRepository) : ViewModel() {

    val allDataTag : LiveData<List<Data>> = repository.allDataTag.asLiveData()
    val allDataDish: LiveData<List<Data>> = repository.allDataDish.asLiveData()
    val allDataRecipe : LiveData<List<Data>> = repository.allDataRecipe.asLiveData()
    val allDataIngredient : LiveData<List<Data>> = repository.allDataIngredient.asLiveData()
    val allDataMeasure : LiveData<List<Data>> = repository.allDataMeasure.asLiveData()
    val allDataAuthor : LiveData<List<Data>> = repository.allDataAuthor.asLiveData()

    fun getNSelected(): LiveData<List<Int>> = repository.getNSelected()

    fun getNSelTag(): LiveData<Int> = repository.getNSelTag().asLiveData()
    fun getNSelDish(): LiveData<Int> = repository.getNSelDish().asLiveData()
    fun getNSelRecipe(): LiveData<Int> = repository.getNSelRecipe().asLiveData()
    fun getNSelIngredient(): LiveData<Int> = repository.getNSelIngredient().asLiveData()
    fun getNSelMeasure(): LiveData<Int> = repository.getNSelMeasure().asLiveData()
    fun getNSelAuthor(): LiveData<Int> = repository.getNSelAuthor().asLiveData()

    fun setData(id: Int, name: String, index: Int) {
        Log.i("--==>", "setData id = $id, name = $name, index = $index")
        repository.setData(id, name, index)
    }

    fun getSelectedId(index: Int): LiveData<List<Int>> = repository.getSelectedId(index).asLiveData()

    fun numerSelected(index: Int): LiveData<Int> = repository.numerSelected(index).asLiveData()

    fun insertTag(name: String) {
        repository.insertTag(name)
    }

    fun selectedOff(id: Int, index: Int) {
        repository.selectedOff(id, index)
    }

    fun selectedOn(id: Int, index: Int) {
        repository.selectedOn(id, index)
    }

    fun insertDish(name: String) {
        repository.insertDish(name)
    }

    fun insertRecipe(name: String) {
        repository.insertRecipe(name)
    }

    fun insertIngredient(name: String) {
        repository.insertIngredient(name)
    }

    fun insertMeasure(name: String) {
        repository.insertMeasure(name)
    }

    fun insertAuthor(name: String) {
        repository.insertAuthor(name)
    }

    fun deleteAll() {
        repository.deleteAllDish()
    }

    fun deleteId(id: Int) {
        repository.deleteIdDish(id)
    }

    fun deleteSelectedId(selectedId: List<Int>, index: Int) {
        repository.deleteSelectedId(selectedId, index)
    }

    fun getDataById(id: Int, index: Int): Any {
        Log.i("--==>", "getDataById id = $id, index = $index")
        return repository.getDataById(id, index)
    }
}

//---------------------------------------------------------------------------------------
class CookViewModelFactory(private val repository: CookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}