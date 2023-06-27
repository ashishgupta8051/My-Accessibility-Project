package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.Users

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase(){
    abstract fun getUserDao(): UserDao
}