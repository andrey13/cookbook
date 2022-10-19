package com.example.cookbook.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [(Dish::class)], version = 1, exportSchema = false)
@Database(entities = [(Dish::class)], version = 1)
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