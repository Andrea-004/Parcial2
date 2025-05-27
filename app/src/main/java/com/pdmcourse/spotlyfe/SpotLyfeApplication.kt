package com.pdmcourse.spotlyfe

import android.app.Application
import com.pdmcourse.spotlyfe.data.AppProvider

class SpotLyfeApplication : Application() {
  lateinit var appProvider: AppProvider
    private set

  override fun onCreate() {
    super.onCreate()
    appProvider = AppProvider.getInstance(this)
  }
}
