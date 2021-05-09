package com.lefarmico.flatstacktesttask.ui.splash

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lefarmico.flatstacktesttask.private.ApiConstants.CLIENT_ID
import com.lefarmico.flatstacktesttask.private.ApiConstants.REDIRECT_URI
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class SplashViewModel : ViewModel() {

    val request = MutableLiveData<AuthorizationRequest>()
    val response = MutableLiveData<AuthorizationResponse>()

    private val streamingScope = arrayOf("streaming")

    init {
        authenticate()
    }

    private fun authenticate() {
        val builder = AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI).apply {
            setScopes(streamingScope)
        }
        request.postValue(builder.build())
    }

    fun setObserverAuthorizationResponse(resultCode: Int, data: Intent?) {
        response.postValue(AuthorizationClient.getResponse(resultCode, data))
    }
}
