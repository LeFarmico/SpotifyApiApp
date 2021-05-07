package com.lefarmico.flatstacktesttask.db.entities

import androidx.room.*
import com.lefarmico.flatstacktesttask.utils.ListOfStringsTypeConverter
import java.io.Serializable

@Entity(tableName = "saves_music", indices = [Index(value = ["title"], unique = false)])
@TypeConverters(
    ListOfStringsTypeConverter::class
)
data class TrackDTO(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val trackName: String,
    @ColumnInfo(name = "artists") val artists: List<String>,
    @ColumnInfo(name = "uri") val uri: String,
    @ColumnInfo(name = "poster_path") val poster: String
) : Serializable

