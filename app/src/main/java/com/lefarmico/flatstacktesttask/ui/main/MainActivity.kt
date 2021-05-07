package com.lefarmico.flatstacktesttask.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lefarmico.flatstacktesttask.R
import com.lefarmico.flatstacktesttask.adapters.TracksAdapter
import com.lefarmico.flatstacktesttask.db.entities.TrackDTO
import com.lefarmico.flatstacktesttask.db.entities.UserDTO
import com.lefarmico.flatstacktesttask.databinding.ActivityMainBinding
import com.lefarmico.flatstacktesttask.ui.details.DetailsFragment
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModels()
    private val detailsFragment = DetailsFragment()

    lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras?.getBundle("LOGIN_BUNDLE")
        token = bundle?.getString("TOKKEN", "")!!

        viewModel.apply {
            setToken(token)
            getTracksDTO()
            getUserInfo()
        }

        viewModel.tracksDTOLiveData.observe(this) {
            showTracks(it)
        }
        viewModel.userDTOLiveData.observe(this) {
            showUser(it)
        }
        Toast.makeText(this, "Login success $token", Toast.LENGTH_SHORT).show()
    }

    private fun showUser(userDTO: UserDTO) {
        binding.displayedNameTextView.text = userDTO.name
        Picasso.get()
            .load(userDTO.image)
            .error(R.drawable.ic_launcher_foreground)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.userPosterImageView)
    }

    private fun showTracks(trackDtoList: List<TrackDTO>) {
        binding.tracksRecyclerView.apply {
            adapter = TracksAdapter {
                val bundle = Bundle()
                bundle.putSerializable("TRACK_INFO", it)
                detailsFragment.arguments = bundle
                detailsFragment.show(supportFragmentManager, "TRACK_DETAILS")
            }.apply {
                setTracks(trackDtoList)
            }
        }
    }
}