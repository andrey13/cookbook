package com.example.cookbook.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CookDao {

    @Query("SELECT * FROM dish")
    fun getAll(): LiveData<List<Dish>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dish: Dish)

    @Query("DELETE FROM dish")
    suspend fun deleteAll()

    @Query("DELETE FROM dish WHERE id = :id")
    suspend fun deleteId(id: Int)

}