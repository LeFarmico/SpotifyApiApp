package com.lefarmico.flatstacktesttask.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lefarmico.flatstacktesttask.R
import com.lefarmico.flatstacktesttask.databinding.DetailsFragmentBinding
import com.lefarmico.flatstacktesttask.db.entities.TrackDTO
import com.lefarmico.flatstacktesttask.private.ApiConstants.TRACK_INFO_KEY
import com.squareup.picasso.Picasso

class DetailsFragment : BottomSheetDialogFragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by activityViewModels()

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
        viewModel.postAppIntent(trackDto.uri, trackDto.href)
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
            viewModel.startAppIntentLiveData.observe(viewLifecycleOwner) {
                startActivity(it)
            }
        }
    }
}
