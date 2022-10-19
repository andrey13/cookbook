package com.example.cookbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.CookRepository
import com.example.cookbook.data.Dish
import kotlinx.coroutines.launch

class CookViewModel(private val repository: CookRepository) : ViewModel() {

    val allDish: LiveData<List<Dish>> = repository.allDishes

    fun insert(dish: Dish) {
        repository.insert(dish)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun deleteId(id: Int) {
        repository.deleteId(id)
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