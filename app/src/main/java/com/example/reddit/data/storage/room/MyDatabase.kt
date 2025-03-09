package com.example.reddit.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reddit.data.models.User
import com.example.reddit.domain.model.Post

@Database(entities = [Post::class, User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun postDao() : PostDao
    abstract fun userDao(): UserDao
}