package com.lefarmico.flatstacktesttask.data.entities.playlistEntity

import com.google.gson.annotations.SerializedName

data class TracksItems(

    @SerializedName("items") var items: List<Items>

)
