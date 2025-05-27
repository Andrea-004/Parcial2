package com.pdmcourse.spotlyfe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmcourse.spotlyfe.data.model.FavoritePlace
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.AddFavoritePlaceScreen
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.SavedPlacesScreen
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.SavedPlacesViewModel

@Composable
fun MainNavigation(navController: NavHostController) {
  NavHost(navController = navController, startDestination = SavedPlacesScreenNavigation) {
    composable<SavedPlacesScreenNavigation> {
      SavedPlacesScreen(navController)
    }

    composable<AddFavoritePlaceScreenNavigation> {
      val viewModel: SavedPlacesViewModel = viewModel(factory = SavedPlacesViewModel.Factory)
      AddFavoritePlaceScreen(
        navController = navController,
        onSave = { name, description, location ->
          viewModel.savePlace(
            FavoritePlace(
              name = name,
              description = description ?: "",
              latitude = location.latitude,
              longitude = location.longitude
            )
          )
        }
      )
    }
  }
}