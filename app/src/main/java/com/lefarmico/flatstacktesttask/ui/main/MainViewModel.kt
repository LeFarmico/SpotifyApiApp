package com.lefarmico.flatstacktesttask.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lefarmico.flatstacktesttask.App
import com.lefarmico.flatstacktesttask.data.Interactor
import com.lefarmico.flatstacktesttask.db.MainRepository
import com.lefarmico.flatstacktesttask.db.entities.TrackDTO
import com.lefarmico.flatstacktesttask.db.entities.UserDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject lateinit var interactor: Interactor
    @Inject lateinit var repository: MainRepository

    private lateinit var token: String

    val userDTOLiveData = MutableLiveData<UserDTO>()
    init {
        App.appComponent.inject(this)
    }
    fun setToken(userToken: String) {
        token = userToken
    }
    fun getTracksDTO() {
        interactor.getPlayListTracks("1FmmPWeCwkds06SAuLjzKM", "Bearer $token")
    }

    fun getUserInfo() {
        interactor.getUserInfo("Bearer $token", this)
    }
    fun setUserDTO(userDTO: UserDTO) {
        userDTOLiveData.postValue(userDTO)
    }
    fun loadTracks(): Flow<List<TrackDTO>> = interactor.getTracksFromDB()
}
