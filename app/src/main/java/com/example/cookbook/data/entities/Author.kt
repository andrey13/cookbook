package com.example.cookbook.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author")
class Author (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name", defaultValue = "")
    val name: String = "",

    @ColumnInfo(name = "rating", defaultValue = "0")
    val rating: Int = 0,

    @ColumnInfo(name = "comment", defaultValue = "")
    val comment: String = ""

)