package com.example.cookbook.data.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration2To3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE author     ADD COLUMN selected INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE dish       ADD COLUMN selected INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE ingredient ADD COLUMN selected INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE measure    ADD COLUMN selected INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE recipe     ADD COLUMN selected INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE stage      ADD COLUMN selected INTEGER NOT NULL DEFAULT 0")
    }
}