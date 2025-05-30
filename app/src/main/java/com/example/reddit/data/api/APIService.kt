package com.example.reddit.data.api

import com.example.reddit.data.api.objects.RedditResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("r/all/top.json")
    suspend fun getPosts(
        @Query("limit") limit : Int = 10,
        @Query("after") after : String? = null,
        @Query("t") time: String = "hour"
    ) : RedditResponse
}