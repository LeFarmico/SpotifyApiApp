package com.lefarmico.flatstacktesttask.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lefarmico.flatstacktesttask.utils.ListOfStringsTypeConverter
import java.io.Serializable

@Entity(tableName = "saved_music", indices = [Index(value = ["uri"], unique = true)])
@TypeConverters(
    ListOfStringsTypeConverter::class
)
data class TrackDTO(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val trackName: String,
    @ColumnInfo(name = "artists") val artists: List<String>,
    @ColumnInfo(name = "uri") val uri: String,
    @ColumnInfo(name = "poster_path") val poster: String,
    @ColumnInfo(name = "href") val href: String
) : Serializable
