package com.example.reddit.data.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reddit.data.models.User

@Dao
interface UserDao {
    @Insert(entity = User::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?
}