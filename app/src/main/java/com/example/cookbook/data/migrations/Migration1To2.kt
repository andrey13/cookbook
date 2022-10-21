package com.example.cookbook.data.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE tag ADD COLUMN selected INTEGER NOT NULL DEFAULT 0")
    }
}












//@RenameTable.Entries(
//    RenameTable(fromTableName = "receipe", toTableName = "recipe"),
//    RenameTable(fromTableName = "autor", toTableName = "author")
//)
//@DeleteTable.Entries(
//    DeleteTable(tableName = "receipe"),
//    DeleteTable(tableName = "autor")
//)
//@RenameColumn(
//    tableName = "d_r",
//    fromColumnName = "id_receipe",
//    toColumnName = "id_recipe"
//)
//@DeleteColumn(
//    tableName = "d_r",
//    columnName = "id_receipe"
//)
//@RenameColumn(
//    tableName = "r_i",
//    fromColumnName = "id_receipe",
//    toColumnName = "id_recipe"
//)
//@DeleteColumn(
//    tableName = "r_i",
//    columnName = "id_receipe"
//)
//@RenameColumn(
//    tableName = "r_s",
//    fromColumnName = "id_receipe",
//    toColumnName = "id_recipe"
//)
//@DeleteColumn(
//    tableName = "r_s",
//    columnName = "id_receipe"
//)
//class CookAutoMigration: AutoMigrationSpec
