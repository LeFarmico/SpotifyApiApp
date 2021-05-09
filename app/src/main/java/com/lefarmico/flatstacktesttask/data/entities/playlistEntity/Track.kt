package com.lefarmico.flatstacktesttask.data.entities.playlistEntity

import com.example.example.Album
import com.google.gson.annotations.SerializedName

data class Track(

    @SerializedName("album") var album: Album,
    @SerializedName("artists") var artists: List<Artists>,
    @SerializedName("uri") var uri: String,
    @SerializedName("name") var name: String,
    @SerializedName("href") val href: String
)
