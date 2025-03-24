package com.example.reddit.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.reddit.data.api.APIService
import com.example.reddit.data.api.objects.PostResponse
import com.example.reddit.presentation.view.PostsPagingSource

class PostsRepository(private val apiService: APIService) {

    fun getPosts(): LiveData<PagingData<PostResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,  // Количество элементов на одной странице
                enablePlaceholders = false // Можно поставить true, если нужны заглушки для незагруженных данных
            ),
            pagingSourceFactory = { PostsPagingSource(apiService) }
        ).liveData
    }
}