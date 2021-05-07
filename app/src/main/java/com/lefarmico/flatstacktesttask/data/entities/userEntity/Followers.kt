package com.example.example

import com.google.gson.annotations.SerializedName

data class Followers(

    @SerializedName("href") var href: String,
    @SerializedName("total") var total: Int

)
