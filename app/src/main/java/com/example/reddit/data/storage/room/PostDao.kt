package com.example.reddit.data.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reddit.domain.model.Post

@Dao
interface PostDao {
    @Insert(entity = Post::class, onConflict = OnConflictStrategy.REPLACE)
    fun putPost(post: Post)

    @Query("SELECT * FROM Post WHERE userId = :userId")
    fun getPostsByUserId(userId: Int): List<Post>

    @Query("DELETE FROM Post WHERE id = :postId")
    suspend fun deletePostById(postId: Int)
}