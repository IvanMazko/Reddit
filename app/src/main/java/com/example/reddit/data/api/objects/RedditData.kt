package com.example.reddit.data.api.objects

import com.google.gson.annotations.SerializedName

class RedditData (
    @SerializedName("children") val children: List<PostWrapper>,
    @SerializedName("after") val after: String?
)