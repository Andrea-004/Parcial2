package com.pdmcourse.spotlyfe.data.model

data class Place(
  val name: String,
  val remark: String,
  val description: String,
  val latitude: Double,
  val longitude: Double,
)

fun Place.toFavoritePlace(): FavoritePlace {
  return FavoritePlace(
    name = name,
    latitude = latitude,
    longitude = longitude,
    description = description
  )
}

