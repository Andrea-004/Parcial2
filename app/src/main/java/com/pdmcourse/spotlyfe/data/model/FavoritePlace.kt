package com.pdmcourse.spotlyfe.data.model

import com.pdmcourse.spotlyfe.data.database.entities.PlaceEntity

data class FavoritePlace(
    val id: Int = 0,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double
)

fun FavoritePlace.toDatabase(): PlaceEntity{
    return PlaceEntity(
        name = name,
        latitude = latitude,
        longitude = longitude,
        description = description
    )
}

fun FavoritePlace.toPlace(): Place{
    return Place (
        name =name,
        remark= "",
        latitude= latitude,
        longitude= longitude,
        description = description
    )
}
