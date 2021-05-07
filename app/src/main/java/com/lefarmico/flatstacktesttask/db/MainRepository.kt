package com.lefarmico.flatstacktesttask.db

import com.lefarmico.flatstacktesttask.db.entities.TrackDTO
import kotlinx.coroutines.flow.Flow

class MainRepository(private val musicDao: MusicDao) {

    suspend fun putTrackToDB(trackDTO: TrackDTO) = musicDao.insertTrack(trackDTO)

    fun getTracksFromDB(): Flow<List<TrackDTO>> = musicDao.getTracks()
}
