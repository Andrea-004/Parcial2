package com.pdmcourse.spotlyfe.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdmcourse.spotlyfe.data.model.FavoritePlace

@Entity(tableName = "places")
data class PlaceEntity(
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
  val name: String,
  val description: String,
  val latitude: Double,
  val longitude: Double
)

fun PlaceEntity.toDomain(): FavoritePlace = FavoritePlace(
  id = id,
  name = name,
  description = description,
  latitude = latitude,
  longitude = longitude
)

fun FavoritePlace.toEntity(): PlaceEntity = PlaceEntity(
  id = id,
  name = name,
  latitude = latitude,
  longitude = longitude,
  description = description
)
