package com.example.example

import com.google.gson.annotations.SerializedName

data class Album(

    @SerializedName("images") var images: List<Poster>

)
