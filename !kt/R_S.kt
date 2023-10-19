package com.example.cookbook.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "r_s",
    indices = [
        Index(value = ["id_recipe", "id_stage"]),
        Index(value = ["id_stage", "id_recipe"]),
    ]
)
class R_S (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "id_recipe")
    val id_recipe: Int = 0,

    @ColumnInfo(name = "id_stage")
    val id_stage: Int = 0

)