package com.example.cookbook

import android.content.Context
import com.example.cookbook.data.CookDao
import com.example.cookbook.data.CookDataBase
import com.example.cookbook.data.CookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CookModule {

    @Provides
    @Singleton
    fun provideCookDataBase(@ApplicationContext context: Context) : CookDataBase =
        CookDataBase.getDatabase(context.applicationContext)

//        Room.databaseBuilder(
//            context.applicationContext,
//            CookDataBase::class.java,
//            "cook_database"
//        ).createFromAsset("cook_database").build()

    @Provides
    @Singleton
    fun provideCookDao(cookDataBase: CookDataBase) : CookDao =
        cookDataBase.cookDao()

    @Provides
    @Singleton
    fun provideCookRepository(cookDao: CookDao) : CookRepository =
        CookRepository(cookDao)

}