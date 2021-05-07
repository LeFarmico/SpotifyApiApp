package com.lefarmico.flatstacktesttask.data

import com.example.example.UserInfo
import com.lefarmico.flatstacktesttask.data.entities.playlistEntity.TracksItems
import retrofit2.Call
import retrofit2.http.*

interface SpotifyApi {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("v1/playlists/{playlist_id}/tracks")
    fun getPlayListTracks(
        @Path("playlist_id") playlistId: String,
        @Header("Authorization") token: String,
        @Query("market") market: String,
        @Query("fields") fields: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Call<TracksItems>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("/v1/me")
    fun getUserInfo(
        @Header("Authorization") token: String
    ): Call<UserInfo>
}
