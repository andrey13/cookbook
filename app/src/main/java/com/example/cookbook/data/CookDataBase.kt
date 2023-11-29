package com.example.cookbook.data

import android.content.Context
import androidx.room.*
import com.example.cookbook.data.entities.*
import com.example.cookbook.data.migrations.Migration1To2
import com.example.cookbook.data.migrations.Migration2To3

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
//    autoMigrations = [
//        AutoMigration(
//            from = 1,
//            to = 2,
//            spec = Migration1To2::class
//        ) ],
    exportSchema = true
)
abstract class CookDataBase : RoomDatabase() {

    abstract fun cookDao(): CookDao


    companion object {

        private val MIGRATION_1_TO_2 = Migration1To2()
        private val MIGRATION_2_TO_3 = Migration2To3()

        //@Volatile
        private var INSTANCE: CookDataBase? = null

        fun getDatabase(context: Context): CookDataBase {
            //Log.i("--==>", "context = $context, this = $this")
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CookDataBase::class.java,
                        "cook_database"
                    )
//                        .fallbackToDestructiveMigration()
//                        .addMigrations(MIGRATION_1_TO_2)
//                        .addMigrations(MIGRATION_2_TO_3)
                        .build()

                    //Log.i("--==>", "instance = $instance")
                    INSTANCE = instance
                }

                return instance
            }

        }
    }
}