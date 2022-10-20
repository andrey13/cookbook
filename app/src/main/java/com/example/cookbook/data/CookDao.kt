package com.example.cookbook.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cookbook.data.entities.*

@Dao
interface CookDao {

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

    //---RECIPE-------------------------------------------------------------------------
    @Query("SELECT id, name  FROM recipe")
    fun getDataRecipe(): LiveData<List<Data>>

    @Query("SELECT * FROM recipe")
    fun getAllRecipe(): LiveData<List<Recipe>>

    @Query("INSERT INTO recipe (name) VALUES (:name)")
    suspend fun insertRecipe(name: String)

    @Query("DELETE FROM recipe")
    suspend fun deleteAllRecipe()

    @Query("DELETE FROM recipe WHERE id = :id")
    suspend fun deleteIdRecipe(id: Int)

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

    //---AUTHOR--------------------------------------------------------------------------
    @Query("SELECT id, name  FROM author")
    fun getDataAuthor(): LiveData<List<Data>>

    @Query("SELECT * FROM author")
    fun getAllAuthor(): LiveData<List<Author>>

    @Query("INSERT INTO author (name) VALUES (:name)")
    suspend fun insertAuthor(name: String)

    @Query("DELETE FROM author")
    suspend fun deleteAllAuthor()

    @Query("DELETE FROM author WHERE id = :id")
    suspend fun deleteIdAuthor(id: Int)

}