package com.example.reddit.data.api.objects

import com.google.gson.annotations.SerializedName

class PostWrapper (
    @SerializedName("data") val post: PostResponse
)