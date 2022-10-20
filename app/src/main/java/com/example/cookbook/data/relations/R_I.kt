package com.example.cookbook.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "r_i",
    indices = [
        Index(value = ["id_receipe", "id_ingredient"]),
        Index(value = ["id_ingredient", "id_receipe"]),
    ]
)
class R_I (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "id_receipe")
    val id_receipe: Int = 0,

    @ColumnInfo(name = "id_ingredient")
    val id_ingredient: Int = 0

)