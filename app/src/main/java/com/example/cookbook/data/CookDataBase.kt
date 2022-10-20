package com.example.cookbook.data

import android.content.Context
import android.util.Log
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cookbook.data.entities.*

//@Database(entities = [(Dish::class)], version = 2, exportSchema = false)
@Database(
    entities = [
        (Autor::class),
        (Dish::class),
        (Ingredient::class),
        (Measure::class),
        (Receipe::class),
        (Stage::class),
        (Tag::class),
        (D_R::class),
        (D_T::class),
        (R_I::class),
        (R_S::class)
    ],
    version = 2,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
abstract class CookDataBase : RoomDatabase() {

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