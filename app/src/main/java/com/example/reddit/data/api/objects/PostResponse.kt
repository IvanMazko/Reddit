package com.example.reddit.data.api.objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.net.URL

@Parcelize
data class PostResponse (
    @SerializedName("author") val author : String,
    @SerializedName("created_utc") val createdUtc : Long,
    @SerializedName("title") val title : String,
    @SerializedName("selftext") val selfText : String,
    @SerializedName("url") val url : String,
    @SerializedName("ups") val ups: Int,
    @SerializedName("downs") val downs : Int,
    @SerializedName("permalink") val permalink : String
):Parcelable