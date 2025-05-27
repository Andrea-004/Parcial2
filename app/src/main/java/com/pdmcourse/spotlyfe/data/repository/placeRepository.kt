package com.pdmcourse.spotlyfe.data.repository
import com.pdmcourse.spotlyfe.data.model.FavoritePlace
import kotlinx.coroutines.flow.Flow

interface placeRepository {
    fun getFavoritePlaces(): Flow<List<FavoritePlace>>
    fun isFavorite(name: String): Flow<Boolean>
    suspend fun addPlaceToFavorites(place: FavoritePlace)
}

