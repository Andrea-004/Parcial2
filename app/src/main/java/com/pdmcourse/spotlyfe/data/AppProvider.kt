package com.pdmcourse.spotlyfe.data

import android.content.Context
import com.pdmcourse.spotlyfe.data.database.AppDatabase
import com.pdmcourse.spotlyfe.data.repository.placeRepository
import com.pdmcourse.spotlyfe.data.repository.PlaceRepositoryImpl

class AppProvider(context: Context) {

  private val appDatabase = AppDatabase.getDatabase(context)
  private val placeDao = appDatabase.placeDao()
  private val placeRepository: placeRepository = PlaceRepositoryImpl(placeDao)

  fun providePlaceRepository(): placeRepository {
    return placeRepository
  }

  companion object {
    @Volatile
    private var INSTANCE: AppProvider? = null

    fun getInstance(context: Context): AppProvider {
      return INSTANCE ?: synchronized(this) {
        INSTANCE ?: AppProvider(context.applicationContext).also {
          INSTANCE = it
        }
      }
    }
  }
}
