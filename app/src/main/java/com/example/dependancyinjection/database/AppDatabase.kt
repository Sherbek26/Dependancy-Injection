package com.example.dependancyinjection.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PostEntity :: class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao() : PostDao
}