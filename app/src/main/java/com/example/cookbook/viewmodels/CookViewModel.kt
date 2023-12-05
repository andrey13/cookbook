package com.example.cookbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.cookbook.data.CookRepository
import com.example.cookbook.data.entities.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CookViewModel @Inject constructor (private val repository: CookRepository) : ViewModel() {

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

    fun setData(
        mode: String,
        id: Int,
        text: String,
        iTab: Int
    ) {
        when (mode) {
            "NEW" -> if (text != "") {
                when (iTab) {
                    0 -> insertTag(text)
                    1 -> insertDish(text)
                    2 -> insertRecipe(text)
                    3 -> insertIngredient(text)
                    4 -> insertMeasure(text)
                    5 -> insertAuthor(text)
                    else -> {}
                }
            }

            "EDIT" -> if (text != "") {
                repository.setData(id, text, iTab)
            }

            else -> {}
        }
    }

    fun getSelectedId(iTab: Int): LiveData<List<Int>> = repository.getSelectedId(iTab).asLiveData()

    fun numberSelected(iTab: Int): LiveData<Int> = repository.numerSelected(iTab).asLiveData()

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