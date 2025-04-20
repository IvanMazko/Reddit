package com.example.reddit.presentation.view

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reddit.data.api.APIService
import com.example.reddit.data.api.objects.PostResponse

class PostsPagingSource (
    private val api : APIService
) : PagingSource<String, PostResponse>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PostResponse> {
        val after = params.key
        val limit = params.loadSize

        return try {
            val response = api.getPosts(limit = limit, after = after) // Делаем запрос к API
            val posts = response.data.children.map { it.post } // Преобразуем ответ в список постов

            LoadResult.Page(
                data = posts, // Загруженные посты
                prevKey = null, // Предыдущего ключа нет (листать назад нельзя)
                nextKey = response.data.after  // Ключ следующей страницы
            )

        } catch (e: Exception) {
            LoadResult.Error(e) // Если ошибка — возвращаем её
        }
    }

    override fun getRefreshKey(state: PagingState<String, PostResponse>): String? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.nextKey
        }
    }

}