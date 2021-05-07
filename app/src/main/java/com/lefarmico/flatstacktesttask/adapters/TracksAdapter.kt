package com.lefarmico.flatstacktesttask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lefarmico.flatstacktesttask.R
import com.lefarmico.flatstacktesttask.db.entities.TrackDTO
import com.lefarmico.flatstacktesttask.databinding.RecyclerTrackBinding
import com.squareup.picasso.Picasso

class TracksAdapter(
    private val listener: (TrackDTO) -> Unit
) : RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

    private var trackDTOList = listOf<TrackDTO>()

    class ViewHolder(trackRecycler: RecyclerTrackBinding) : RecyclerView.ViewHolder(trackRecycler.root) {
        private val poster: ImageView = trackRecycler.trackPosterImageView
        private val name: TextView = trackRecycler.trackNameTextView
        private val artists: TextView = trackRecycler.trackArtistsTextView

        fun bind(trackDTO: TrackDTO) {
            name.text = trackDTO.trackName
            artists.text = trackDTO.artists.joinToString(", ")
            Picasso.get()
                .load(trackDTO.poster)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RecyclerTrackBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trackDTOList[position])
        holder.itemView.setOnClickListener {
            listener(trackDTOList[position])
        }
    }

    override fun getItemCount(): Int = trackDTOList.size

    fun setTracks(trackDtoItemsList: List<TrackDTO>) {
        trackDTOList = trackDtoItemsList
        notifyDataSetChanged()
    }
}
