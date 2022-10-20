package com.example.cookbook.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "r_i",
    indices = [
        Index(value = ["id_recipe", "id_ingredient"]),
        Index(value = ["id_ingredient", "id_recipe"]),
    ]
)
class R_I (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "id_recipe")
    val id_recipe: Int = 0,

    @ColumnInfo(name = "id_ingredient")
    val id_ingredient: Int = 0

)