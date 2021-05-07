package com.lefarmico.flatstacktesttask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lefarmico.flatstacktesttask.db.entities.TrackDTO

@Database(
    entities = [
        TrackDTO::class
    ],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun musicDao(): MusicDao
}
