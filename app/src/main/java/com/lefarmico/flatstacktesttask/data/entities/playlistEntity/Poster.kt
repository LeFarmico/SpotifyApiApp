package com.example.example

import com.google.gson.annotations.SerializedName

data class Poster(

    @SerializedName("height") var height: Int,
    @SerializedName("url") var url: String,
    @SerializedName("width") var width: Int

)
