package com.example.reddit.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val header : String,
    @ColumnInfo val message : String,
    val date : String,
    @ColumnInfo val userId: Int // добавляем связь с пользователем
)