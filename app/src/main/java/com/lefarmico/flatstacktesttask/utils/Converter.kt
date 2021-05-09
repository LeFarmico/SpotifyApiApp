package com.lefarmico.flatstacktesttask.utils

import com.example.example.UserInfo
import com.lefarmico.flatstacktesttask.data.entities.playlistEntity.Items
import com.lefarmico.flatstacktesttask.db.entities.TrackDTO
import com.lefarmico.flatstacktesttask.db.entities.UserDTO

object Converter {

    fun convertFromTracksItemsToTracksDTO(items: List<Items>?): List<TrackDTO> {
        val trackDTOList = mutableListOf<TrackDTO>()
        items?.forEach {
            val artists = mutableListOf<String>()
            for (i in it.track.artists.indices) {
                artists.add(it.track.artists[i].name)
            }
            trackDTOList.add(
                TrackDTO(
                    trackName = it.track.name,
                    artists = artists,
                    uri = it.track.uri,
                    poster = it.track.album.images[0].url,
                    href = it.track.href
                )
            )
        }
        return trackDTOList
    }

    fun convertUserInfoToUserDTO(userInfo: UserInfo?): UserDTO {
        return UserDTO(
            name = userInfo?.displayName!!,
            image = userInfo.images[0].url

        )
    }
}
