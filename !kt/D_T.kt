package com.example.cookbook.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "d_t",
    indices = [
        Index(value = ["id_dish", "id_tag"]),
        Index(value = ["id_tag", "id_dish"]),
    ]
)
class D_T (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "id_dish")
    val id_dish: Int = 0,

    @ColumnInfo(name = "id_tag")
    val id_tag: Int = 0

)