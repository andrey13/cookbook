package com.example.cookbook.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "d_r",
    indices = [
        Index(value = ["id_dish", "id_receipe"]),
        Index(value = ["id_receipe", "id_dish"]),
    ]
)
class D_R (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "id_dish")
    val id_dish: Int = 0,

    @ColumnInfo(name = "id_receipe")
    val id_receipe: Int = 0

)