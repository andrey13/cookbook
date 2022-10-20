package com.example.cookbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.data.CookRepository
import com.example.cookbook.data.entities.Data

class CookViewModel(private val repository: CookRepository) : ViewModel() {

    val allDataTag : LiveData<List<Data>> = repository.allDataTag
    val allDataDish: LiveData<List<Data>> = repository.allDataDishes
    val allDataReceipe : LiveData<List<Data>> = repository.allDataReceipe
    val allDataIngredient : LiveData<List<Data>> = repository.allDataIngredient
    val allDataMeasure : LiveData<List<Data>> = repository.allDataMeasure
    val allDataAutor : LiveData<List<Data>> = repository.allDataAutor

    fun insertTag(name: String) {
        repository.insertTag(name)
    }

    fun insertDish(name: String) {
        repository.insertDish(name)
    }

    fun insertReceipe(name: String) {
        repository.insertReceipe(name)
    }

    fun insertIngredient(name: String) {
        repository.insertIngredient(name)
    }

    fun insertaMeasure(name: String) {
        repository.insertMeasure(name)
    }

    fun insertAutor(name: String) {
        repository.insertAutor(name)
    }

    fun deleteAll() {
        repository.deleteAllDish()
    }

    fun deleteId(id: Int) {
        repository.deleteIdDish(id)
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