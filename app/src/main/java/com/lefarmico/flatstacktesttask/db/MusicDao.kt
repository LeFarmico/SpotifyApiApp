package com.lefarmico.flatstacktesttask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lefarmico.flatstacktesttask.db.entities.TrackDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTrack(trackDTO: TrackDTO)

    @Query("SELECT * FROM saved_music")
    fun getTracks(): Flow<List<TrackDTO>>
}
