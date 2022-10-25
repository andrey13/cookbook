package com.example.cookbook.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "measure")
class Measure (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Int = 0,

    @ColumnInfo(name = "name", defaultValue = "")
    override val name: String = "",

    @ColumnInfo(name = "grams", defaultValue = "0")
    val grams: Int = 0,

    @ColumnInfo(name = "comment", defaultValue = "")
    val comment: String = "",

    @ColumnInfo(name = "selected", defaultValue = "0")
    override val selected: Int = 0

) : Data(id, name, selected)