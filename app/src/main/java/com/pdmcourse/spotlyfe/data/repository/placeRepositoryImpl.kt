package com.pdmcourse.spotlyfe.data.repository

import com.pdmcourse.spotlyfe.data.database.dao.PlaceDao
import com.pdmcourse.spotlyfe.data.database.entities.toDomain
import com.pdmcourse.spotlyfe.data.database.entities.toEntity
import com.pdmcourse.spotlyfe.data.model.FavoritePlace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaceRepositoryImpl(
    private val placeDao: PlaceDao
) : placeRepository {

    override fun getFavoritePlaces(): Flow<List<FavoritePlace>> {
        return placeDao.getAllPlaces().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun isFavorite(name: String): Flow<Boolean> {
        return placeDao.getAllPlaces().map { list ->
            list.any { it.name == name }
        }
    }

    override suspend fun addPlaceToFavorites(place: FavoritePlace) {
        placeDao.insertPlace(place.toEntity())
    }
}
