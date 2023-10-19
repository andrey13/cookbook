package com.example.cookbook.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class Recipe (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Int = 0,

    @ColumnInfo(name = "name", defaultValue = "")
    override val name: String = "",

    @ColumnInfo(name = "rating", defaultValue = "0")
    val rating: Int = 0,

    @ColumnInfo(name = "persons", defaultValue = "0")
    val persons: Int = 0,

    @ColumnInfo(name = "time", defaultValue = "0")
    val time: Int = 0,

    @ColumnInfo(name = "id_autor", defaultValue = "0")
    val id_autor: Int = 0,

    @ColumnInfo(name = "url", defaultValue = "")
    val url: String = "",

    @ColumnInfo(name = "comment", defaultValue = "")
    val comment: String = "",

    @ColumnInfo(name = "selected", defaultValue = "0")
    override val selected: Int = 0

) : Data(id, name, selected)