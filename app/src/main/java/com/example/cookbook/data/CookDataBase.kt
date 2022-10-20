package com.example.cookbook.data

import android.content.Context
import android.util.Log
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.example.cookbook.data.entities.*

@Database(
    entities = [
        (Author::class),
        (Dish::class),
        (Ingredient::class),
        (Measure::class),
        (Recipe::class),
        (Stage::class),
        (Tag::class),
        (D_R::class),
        (D_T::class),
        (R_I::class),
        (R_S::class)
    ],
    version = 1,
    autoMigrations = [
        AutoMigration (
            from = 2,
            to = 3,
            spec = CookDataBase.CookAutoMigration::class
        )
    ]
)
abstract class CookDataBase : RoomDatabase() {

    @RenameTable.Entries(
        RenameTable(fromTableName = "receipe", toTableName = "recipe"),
        RenameTable(fromTableName = "autor", toTableName = "author")
    )
    @DeleteTable.Entries(
        DeleteTable(tableName = "receipe"),
        DeleteTable(tableName = "autor")
    )
    @RenameColumn(
        tableName = "d_r",
        fromColumnName = "id_receipe",
        toColumnName = "id_recipe"
    )
    @DeleteColumn(
        tableName = "d_r",
        columnName = "id_receipe"
    )
    @RenameColumn(
        tableName = "r_i",
        fromColumnName = "id_receipe",
        toColumnName = "id_recipe"
    )
    @DeleteColumn(
        tableName = "r_i",
        columnName = "id_receipe"
    )
    @RenameColumn(
        tableName = "r_s",
        fromColumnName = "id_receipe",
        toColumnName = "id_recipe"
    )
    @DeleteColumn(
        tableName = "r_s",
        columnName = "id_receipe"
    )
    class CookAutoMigration: AutoMigrationSpec

    abstract fun cookDao(): CookDao

    companion object {
        //@Volatile
        private var INSTANCE: CookDataBase? = null

        fun getDatabase(context: Context): CookDataBase {
            Log.i("--==>", "context = $context, this = $this")
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CookDataBase::class.java,
                        "cook_database"
                    ).fallbackToDestructiveMigration().build()
                    Log.i("--==>", "instance = $instance")
                    INSTANCE = instance
                }

                return instance
            }

        }
    }
}