package com.example.example

import com.google.gson.annotations.SerializedName

   
data class ExplicitContent (

   @SerializedName("filter_enabled") var filterEnabled : Boolean,
   @SerializedName("filter_locked") var filterLocked : Boolean

)