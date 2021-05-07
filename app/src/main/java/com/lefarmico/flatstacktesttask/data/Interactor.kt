package com.lefarmico.flatstacktesttask.data

import android.util.Log
import com.example.example.UserInfo
import com.lefarmico.flatstacktesttask.data.entities.playlistEntity.TracksItems
import com.lefarmico.flatstacktesttask.db.MainRepository
import com.lefarmico.flatstacktesttask.ui.main.MainViewModel
import com.lefarmico.flatstacktesttask.utils.Converter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: SpotifyApi) {

    fun getPlayListTracks(playlistId: String, token: String, mainViewModel: MainViewModel) {
        retrofitService.getPlayListTracks(
            playlistId,
            token,
            "ES",
            "items(id,track(artists(name),name,album(images),uri))",
            20,
            40
        ).enqueue(object : Callback<TracksItems> {
            override fun onResponse(call: Call<TracksItems>, response: Response<TracksItems>) {
                val trackDTOList = Converter.convertFromTracksItemsToTracksDTO(response.body()?.items)
                mainViewModel.setTracksDTO(trackDTOList)
            }

            override fun onFailure(call: Call<TracksItems>, t: Throwable) {
                Log.e("Interactor", t.message!!)
            }
        })
    }

    fun getUserInfo(token: String, mainViewModel: MainViewModel) {
        retrofitService.getUserInfo(token).enqueue(object : Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                val userDTO = Converter.convertUserInfoToUserDTO(response.body())
                mainViewModel.setUserDTO(userDTO)
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.e("Interactor", t.message!!)
            }
        })
    }
}
