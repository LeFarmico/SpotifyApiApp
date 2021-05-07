package com.lefarmico.flatstacktesttask.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lefarmico.flatstacktesttask.databinding.ActivitySplashBinding
import com.lefarmico.flatstacktesttask.private.ApiConstants.BUNDLE_NAME
import com.lefarmico.flatstacktesttask.private.ApiConstants.REQUEST_CODE
import com.lefarmico.flatstacktesttask.private.ApiConstants.TOKEN_KEY
import com.lefarmico.flatstacktesttask.ui.main.MainActivity
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.sdk.android.auth.AuthorizationResponse.Type.EMPTY
import com.spotify.sdk.android.auth.AuthorizationResponse.Type.ERROR
import com.spotify.sdk.android.auth.AuthorizationResponse.Type.TOKEN
import com.spotify.sdk.android.auth.AuthorizationResponse.Type.UNKNOWN

class SplashActivity : AppCompatActivity() {

    val viewModel: SplashViewModel by viewModels()
    private val TAG = "SPOTIFY"
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.request.observe(this) { request ->
            binding.loginButton.setOnClickListener {
                login(request)
            }
        }

        viewModel.response.observe(this) { response ->
            when (response.type) {
                TOKEN -> connected(response)
                ERROR -> onFailureConnect(response)
                EMPTY -> onEmptyResponse(response)
                UNKNOWN -> Log.d(TAG, "UnknownError!!!")
                else -> {}
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            viewModel.setObserverAuthorizationResponse(resultCode, data)
        }
    }

    private fun login(request: AuthorizationRequest) {
        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)
    }
    private fun connected(response: AuthorizationResponse) {
        Log.d(TAG, "Login success")
        val tokenIntent = Intent(this, MainActivity::class.java).apply {
            val bundle = Bundle().apply {
                putString(TOKEN_KEY, response.accessToken)
            }
            putExtra(BUNDLE_NAME, bundle)
        }
        startActivity(tokenIntent)
    }
    private fun onFailureConnect(response: AuthorizationResponse) {
        Toast.makeText(this, "Login error, try again.", Toast.LENGTH_SHORT).show()
        Log.e(TAG, response.error)
    }
    private fun onEmptyResponse(response: AuthorizationResponse) {
        Toast.makeText(
            this,
            "Response doesn't contain data because auth flow was cancelled or LoginActivity killed.",
            Toast.LENGTH_SHORT
        ).show()
        Log.e(TAG, response.error)
    }
}
