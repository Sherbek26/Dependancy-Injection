package com.example.dependancyinjection.di

import android.content.Context
import androidx.room.Room
import com.example.dependancyinjection.api.ApiService
import com.example.dependancyinjection.database.AppDatabase
import com.example.dependancyinjection.database.PostDao
import com.example.dependancyinjection.utility.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModel {

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context) : NetworkHelper{
        return NetworkHelper(context)
    }


    @Singleton
    @Provides
    fun provideService() : ApiService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,"app_database")
            .build()
    }

    @Singleton
    @Provides
    fun getPostDao(appDatabase: AppDatabase) : PostDao {
        return appDatabase.postDao()
    }
}