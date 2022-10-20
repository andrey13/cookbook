package com.example.cookbook.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cookbook.data.entities.*

@Dao
interface CookDao {

    //---DISH----------------------------------------------------------------------------
    @Query("SELECT id, name  FROM dish")
    fun getDataDish(): LiveData<List<Data>>

    @Query("SELECT * FROM dish")
    fun getAllDish(): LiveData<List<Dish>>

    @Query("INSERT INTO dish (name) VALUES (:name)")
    suspend fun insertDish(name: String)

    @Query("DELETE FROM dish")
    suspend fun deleteAllDish()

    @Query("DELETE FROM dish WHERE id = :id")
    suspend fun deleteIdDish(id: Int)

    //---TAG-----------------------------------------------------------------------------
    @Query("SELECT id, name  FROM tag")
    fun getDataTag(): LiveData<List<Data>>

    @Query("SELECT * FROM tag")
    fun getAllTag(): LiveData<List<Tag>>

    @Query("INSERT INTO tag (name) VALUES (:name)")
    suspend fun insertTag(name: String)

    @Query("DELETE FROM tag")
    suspend fun deleteAllTag()

    @Query("DELETE FROM tag WHERE id = :id")
    suspend fun deleteIdTag(id: Int)

    //---INGREDIENT----------------------------------------------------------------------
    @Query("SELECT id, name  FROM ingredient")
    fun getDataIngredient(): LiveData<List<Data>>

    @Query("SELECT * FROM ingredient")
    fun getAllIngredient(): LiveData<List<Ingredient>>

    @Query("INSERT INTO ingredient (name) VALUES (:name)")
    suspend fun insertIngredient(name: String)

    @Query("DELETE FROM ingredient")
    suspend fun deleteAllIngredient()

    @Query("DELETE FROM ingredient WHERE id = :id")
    suspend fun deleteIdIngredient(id: Int)

    //---RECEIPE-------------------------------------------------------------------------
    @Query("SELECT id, name  FROM receipe")
    fun getDataReceipe(): LiveData<List<Data>>

    @Query("SELECT * FROM receipe")
    fun getAllReceipe(): LiveData<List<Receipe>>

    @Query("INSERT INTO receipe (name) VALUES (:name)")
    suspend fun insertReceipe(name: String)

    @Query("DELETE FROM receipe")
    suspend fun deleteAllReceipe()

    @Query("DELETE FROM receipe WHERE id = :id")
    suspend fun deleteIdReceipe(id: Int)

    //---MEASURE-------------------------------------------------------------------------
    @Query("SELECT id, name  FROM measure")
    fun getDataMeasure(): LiveData<List<Data>>

    @Query("SELECT * FROM measure")
    fun getAllMeasure(): LiveData<List<Measure>>

    @Query("INSERT INTO measure (name) VALUES (:name)")
    suspend fun insertMeasure(name: String)

    @Query("DELETE FROM measure")
    suspend fun deleteAllMeasure()

    @Query("DELETE FROM measure WHERE id = :id")
    suspend fun deleteIdMeasure(id: Int)

    //---AUTOR---------------------------------------------------------------------------
    @Query("SELECT id, name  FROM autor")
    fun getDataAutor(): LiveData<List<Data>>

    @Query("SELECT * FROM autor")
    fun getAllAutor(): LiveData<List<Autor>>

    @Query("INSERT INTO autor (name) VALUES (:name)")
    suspend fun insertAutor(name: String)

    @Query("DELETE FROM autor")
    suspend fun deleteAllAutor()

    @Query("DELETE FROM autor WHERE id = :id")
    suspend fun deleteIdAutor(id: Int)

}