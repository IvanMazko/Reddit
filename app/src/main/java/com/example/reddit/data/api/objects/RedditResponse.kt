package com.example.reddit.data.api.objects

import com.google.gson.annotations.SerializedName

class RedditResponse (
    @SerializedName("data") val data : RedditData
)