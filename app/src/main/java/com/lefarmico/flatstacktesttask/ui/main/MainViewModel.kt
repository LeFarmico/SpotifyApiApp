package com.lefarmico.flatstacktesttask.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lefarmico.flatstacktesttask.App
import com.lefarmico.flatstacktesttask.data.Interactor
import com.lefarmico.flatstacktesttask.db.entities.TrackDTO
import com.lefarmico.flatstacktesttask.db.entities.UserDTO
import com.lefarmico.flatstacktesttask.db.MainRepository
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject lateinit var interactor: Interactor
    @Inject lateinit var repository: MainRepository

    private lateinit var token: String

    val tracksDTOLiveData = MutableLiveData<List<TrackDTO>>()
    val userDTOLiveData = MutableLiveData<UserDTO>()
    init {
        App.appComponent.inject(this)
    }
    fun setToken(userToken: String) {
        token = userToken
    }
    fun getTracksDTO() {
        interactor.getPlayListTracks("1FmmPWeCwkds06SAuLjzKM", "Bearer $token", this)
    }
    fun setTracksDTO(trackDTOList: List<TrackDTO>) {
        tracksDTOLiveData.postValue(trackDTOList)
    }
    fun getUserInfo() {
        interactor.getUserInfo("Bearer $token", this)
    }
    fun setUserDTO(userDTO: UserDTO) {
        userDTOLiveData.postValue(userDTO)
    }
}
