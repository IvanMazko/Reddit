package com.example.reddit.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitImpl {
    val api: APIService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.reddit.com/dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}