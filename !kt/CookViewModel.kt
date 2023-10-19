package com.example.cookbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.cookbook.data.CookRepository
import com.example.cookbook.data.entities.Data

class CookViewModel(private val repository: CookRepository) : ViewModel() {

    val liveData1 = repository.liveData1
    val liveData2 = repository.liveData2
    val liveData3 = repository.liveData3

    val allDataTag : LiveData<List<Data>> = repository.getDataTag().asLiveData()
    val allDataDish: LiveData<List<Data>> = repository.getDataDish().asLiveData()
    val allDataRecipe : LiveData<List<Data>> = repository.getDataRecipe().asLiveData()
    val allDataIngredient : LiveData<List<Data>> = repository.getDataIngredient().asLiveData()
    val allDataMeasure : LiveData<List<Data>> = repository.getDataMeasure().asLiveData()
    val allDataAuthor : LiveData<List<Data>> = repository.getDataAuthor().asLiveData()

    fun getNSelTag(): LiveData<Int> = repository.getNSelTag().asLiveData()
    fun getNSelDish(): LiveData<Int> = repository.getNSelDish().asLiveData()
    fun getNSelRecipe(): LiveData<Int> = repository.getNSelRecipe().asLiveData()
    fun getNSelIngredient(): LiveData<Int> = repository.getNSelIngredient().asLiveData()
    fun getNSelMeasure(): LiveData<Int> = repository.getNSelMeasure().asLiveData()
    fun getNSelAuthor(): LiveData<Int> = repository.getNSelAuthor().asLiveData()

    fun setData(id: Int, name: String, iTab: Int) {
        repository.setData(id, name, iTab)
    }

    fun getSelectedId(iTab: Int): LiveData<List<Int>> = repository.getSelectedId(iTab).asLiveData()

    fun numerSelected(iTab: Int): LiveData<Int> = repository.numerSelected(iTab).asLiveData()

    fun insertTag(name: String) {
        repository.insertTag(name)
    }

    fun selectedOff(id: Int, iTab: Int) {
        repository.selectedOff(id, iTab)
    }

    fun selectedOn(id: Int, iTab: Int) {
        repository.selectedOn(id, iTab)
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

    fun deleteSelectedId(selectedId: List<Int>, iTab: Int) {
        repository.deleteSelectedId(selectedId, iTab)
    }

    fun getDataById(id: Int, iTab: Int): Any {
        return repository.getDataById(id, iTab)
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