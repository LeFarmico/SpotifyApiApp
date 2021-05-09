package com.lefarmico.flatstacktesttask.ui.details

import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val context: Application) : AndroidViewModel(context) {

    val startAppIntentLiveData = MutableLiveData<Intent>()
    private val scope = CoroutineScope(Dispatchers.IO)

    fun postAppIntent(trackUri: String, trackHref: String) {
        Log.e("AppCheck", context.packageName)
        scope.launch {
            if (isAppInstalled(context.packageManager)) {
                Log.d("AppCheck", "App is exist")
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(trackUri)

                intent.putExtra(
                    Intent.EXTRA_REFERRER,
                    Uri.parse("android-app://" + context.packageName)
                )
                startAppIntentLiveData.postValue(intent)
            } else {
                Log.d("AppCheck", "App is not exist")
                val clipedUri = trackHref.split("/")
                val trackId = clipedUri[clipedUri.size - 1]
                val uri = Uri.parse("https://open.spotify.com/track/$trackId")
                    .buildUpon()
                    .appendQueryParameter("id", "com.spotify.music")
                    .build()
                startAppIntentLiveData.postValue(Intent(Intent.ACTION_VIEW, uri))
            }
        }
    }
    private fun isAppInstalled(packageManager: PackageManager): Boolean {
        return try {
            packageManager.getApplicationInfo("com.spotify.music", 0).enabled
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }
}
