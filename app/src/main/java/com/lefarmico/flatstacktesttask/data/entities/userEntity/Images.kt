package com.example.example

import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("height") var height: String,
    @SerializedName("url") var url: String,
    @SerializedName("width") var width: String

)
