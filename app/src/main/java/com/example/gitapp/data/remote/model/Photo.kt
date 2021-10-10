package com.example.gitapp.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Photo(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("download_url")
    val download_url: String? = null,

    // inner variable
    val loadMoreTag: String? = null
) : Serializable