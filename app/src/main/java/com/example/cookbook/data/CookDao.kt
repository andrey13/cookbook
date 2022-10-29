package com.example.cookbook.data

import androidx.room.*
import com.example.cookbook.data.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CookDao {

    //---SELECT *  FROM <> WHERE id = :id  --> Flow<Data> ---------------------------

    @Query("SELECT * FROM tag WHERE id = :id")
    suspend fun getDataByIdTag(id: Int): Tag

    @Query("SELECT * FROM dish WHERE id = :id")
    suspend fun getDataByIdDish(id: Int): Dish

    @Query("SELECT * FROM recipe WHERE id = :id")
    suspend fun getDataByIdRecipe(id: Int): Recipe

    @Query("SELECT * FROM ingredient WHERE id = :id")
    suspend fun getDataByIdIngredient(id: Int): Ingredient

    @Query("SELECT * FROM measure WHERE id = :id")
    suspend fun getDataByIdMeasure(id: Int): Measure

    @Query("SELECT * FROM author WHERE id = :id")
    suspend fun getDataByIdAuthor(id: Int): Author

    //---TAG-----------------------------------------------------------------------------
    
    @Query("SELECT id, name, selected  FROM tag ORDER BY name")
    fun getDataTag(): Flow<List<Data>>

    @Query("UPDATE tag SET name = :name WHERE id = :id")
    suspend fun setDataTag(id: Int, name: String)

    @Query("SELECT id FROM tag WHERE selected = 1")
    fun getSelectedIdTag(): Flow<List<Int>>

    @Query("SELECT * FROM tag")
    fun getAllTag(): Flow<List<Tag>>

    @Query("INSERT INTO tag (name) VALUES (:name)")
    suspend fun insertTag(name: String)

    @Query("DELETE FROM tag")
    suspend fun deleteAllTag()

    @Query("DELETE FROM tag WHERE id = :id")
    suspend fun deleteIdTag(id: Int)

    @Query("UPDATE tag SET selected = 0 WHERE id = :id"  )
    suspend fun selectedOffTag(id: Int)

    @Query("UPDATE tag SET selected = 1 WHERE id = :id"  )
    suspend fun selectedOnTag(id: Int)

    @Query("SELECT COUNT() FROM tag WHERE selected = 1")
    fun numerSelTag(): Flow<Int>

    @Query("SELECT COUNT() FROM tag WHERE selected = 1")
    suspend fun numerSelectedTag(): Int

    //---DISH----------------------------------------------------------------------------
    @Query("SELECT id, name, selected  FROM dish ORDER BY name")
    fun getDataDish(): Flow<List<Data>>

    @Query("UPDATE dish SET name = :name WHERE id = :id")
    suspend fun setDataDish(id: Int, name: String)

    @Query("SELECT id FROM dish WHERE selected = 1")
    fun getSelectedIdDish(): Flow<List<Int>>

    @Query("SELECT * FROM dish")
    fun getAllDish(): Flow<List<Dish>>

    @Query("INSERT INTO dish (name) VALUES (:name)")
    suspend fun insertDish(name: String)

    @Query("DELETE FROM dish")
    suspend fun deleteAllDish()

    @Query("DELETE FROM dish WHERE id = :id")
    suspend fun deleteIdDish(id: Int)

    @Query("UPDATE dish SET selected = 0 WHERE id = :id"  )
    suspend fun selectedOffDish(id: Int)

    @Query("UPDATE dish SET selected = 1 WHERE id = :id"  )
    suspend fun selectedOnDish(id: Int)

    @Query("SELECT COUNT(selected) FROM dish WHERE selected = 1")
    fun numerSelDish():  Flow<Int>

    @Query("SELECT COUNT(selected) FROM dish WHERE selected = 1")
    suspend fun numerSelectedDish():  Int

    //---RECIPE-------------------------------------------------------------------------
    @Query("SELECT id, name, selected  FROM recipe ORDER BY name")
    fun getDataRecipe(): Flow<List<Data>>

    @Query("UPDATE recipe SET name = :name WHERE id = :id")
    suspend fun setDataRecipe(id: Int, name: String)

    @Query("SELECT id FROM recipe WHERE selected = 1")
    fun getSelectedIdRecipe(): Flow<List<Int>>

    @Query("SELECT * FROM recipe")
    fun getAllRecipe(): Flow<List<Recipe>>

    @Query("INSERT INTO recipe (name) VALUES (:name)")
    suspend fun insertRecipe(name: String)

    @Query("DELETE FROM recipe")
    suspend fun deleteAllRecipe()

    @Query("DELETE FROM recipe WHERE id = :id")
    suspend fun deleteIdRecipe(id: Int)

    @Query("UPDATE recipe SET selected = 0 WHERE id = :id"  )
    suspend fun selectedOffRecipe(id: Int)

    @Query("UPDATE recipe SET selected = 1 WHERE id = :id"  )
    suspend fun selectedOnRecipe(id: Int)

    @Query("SELECT COUNT(selected) FROM recipe WHERE selected = 1")
    fun numerSelRecipe():  Flow<Int>

    @Query("SELECT COUNT(selected) FROM recipe WHERE selected = 1")
    suspend fun numerSelectedRecipe():  Int

    //---INGREDIENT----------------------------------------------------------------------
    @Query("SELECT id, name, selected  FROM ingredient ORDER BY name")
    fun getDataIngredient(): Flow<List<Data>>

    @Query("UPDATE ingredient SET name = :name WHERE id = :id")
    suspend fun setDataIngredient(id: Int, name: String)

    @Query("SELECT id FROM ingredient WHERE selected = 1")
    fun getSelectedIdIngredient(): Flow<List<Int>>

    @Query("SELECT * FROM ingredient")
    fun getAllIngredient(): Flow<List<Ingredient>>

    @Query("INSERT INTO ingredient (name) VALUES (:name)")
    suspend fun insertIngredient(name: String)

    @Query("DELETE FROM ingredient")
    suspend fun deleteAllIngredient()

    @Query("DELETE FROM ingredient WHERE id = :id")
    suspend fun deleteIdIngredient(id: Int)

    @Query("UPDATE ingredient SET selected = 0 WHERE id = :id"  )
    suspend fun selectedOffIngredient(id: Int)

    @Query("UPDATE ingredient SET selected = 1 WHERE id = :id"  )
    suspend fun selectedOnIngredient(id: Int)

    @Query("SELECT COUNT(selected) FROM ingredient WHERE selected = 1")
    fun numerSelIngredient():  Flow<Int>

    @Query("SELECT COUNT(selected) FROM ingredient WHERE selected = 1")
    suspend fun numerSelectedIngredient():  Int

    //---MEASURE-------------------------------------------------------------------------
    @Query("SELECT id, name, selected  FROM measure ORDER BY name")
    fun getDataMeasure(): Flow<List<Data>>

    @Query("UPDATE measure SET name = :name WHERE id = :id")
    suspend fun setDataMeasure(id: Int, name: String)

    @Query("SELECT id FROM measure WHERE selected = 1")
    fun getSelectedIdMeasure(): Flow<List<Int>>

    @Query("SELECT * FROM measure")
    fun getAllMeasure(): Flow<List<Measure>>

    @Query("INSERT INTO measure (name) VALUES (:name)")
    suspend fun insertMeasure(name: String)

    @Query("DELETE FROM measure")
    suspend fun deleteAllMeasure()

    @Query("DELETE FROM measure WHERE id = :id")
    suspend fun deleteIdMeasure(id: Int)

    @Query("UPDATE measure SET selected = 0 WHERE id = :id"  )
    suspend fun selectedOffMeasure(id: Int)

    @Query("UPDATE measure SET selected = 1 WHERE id = :id"  )
    suspend fun selectedOnMeasure(id: Int)

    @Query("SELECT COUNT(selected) FROM measure WHERE selected = 1")
    fun numerSelMeasure():  Flow<Int>

    @Query("SELECT COUNT(selected) FROM measure WHERE selected = 1")
    suspend fun numerSelectedMeasure():  Int

    //---AUTHOR--------------------------------------------------------------------------
    @Query("SELECT id, name, selected  FROM author ORDER BY name")
    fun getDataAuthor(): Flow<List<Data>>

    @Query("UPDATE author SET name = :name WHERE id = :id")
    suspend fun setDataAuthor(id: Int, name: String)

    @Query("SELECT id FROM author WHERE selected = 1")
    fun getSelectedIdAuthor(): Flow<List<Int>>

    @Query("SELECT * FROM author")
    fun getAllAuthor(): Flow<List<Author>>

    @Query("INSERT INTO author (name) VALUES (:name)")
    suspend fun insertAuthor(name: String)

    @Query("DELETE FROM author")
    suspend fun deleteAllAuthor()

    @Query("DELETE FROM author WHERE id = :id")
    suspend fun deleteIdAuthor(id: Int)

    @Query("UPDATE author SET selected = 0 WHERE id = :id"  )
    suspend fun selectedOffAuthor(id: Int)

    @Query("UPDATE author SET selected = 1 WHERE id = :id"  )
    suspend fun selectedOnAuthor(id: Int)

    @Query("SELECT COUNT(selected) FROM author WHERE selected = 1")
    fun numerSelAuthor():  Flow<Int>

    @Query("SELECT COUNT(selected) FROM author WHERE selected = 1")
    suspend fun numerSelectedAuthor():  Int
}