package com.lefarmico.flatstacktesttask.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lefarmico.flatstacktesttask.R
import com.lefarmico.flatstacktesttask.databinding.DetailsFragmentBinding
import com.lefarmico.flatstacktesttask.db.entities.TrackDTO
import com.lefarmico.flatstacktesttask.private.ApiConstants.TRACK_INFO_KEY
import com.squareup.picasso.Picasso

class DetailsFragment : BottomSheetDialogFragment() {

    private val bundle = this.arguments

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trackDto = arguments?.getSerializable(TRACK_INFO_KEY) as TrackDTO
        showTrack(trackDto)
    }

    private fun showTrack(trackDto: TrackDTO) {
        Picasso.get()
            .load(trackDto.poster)
            .error(R.drawable.ic_launcher_foreground)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.posterImageView)
        binding.trackArtistsTextView.text = trackDto.artists.joinToString(", ")
        binding.trackNameTextView.text = trackDto.trackName
        binding.playOnSpotifyButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(trackDto.uri)
            intent.putExtra(
                Intent.EXTRA_REFERRER,
                Uri.parse("android-app://" + requireContext().packageName)
            )
            startActivity(intent)
        }
    }
}
