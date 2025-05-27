package com.pdmcourse.spotlyfe.ui.screens.SavedPlaces

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pdmcourse.spotlyfe.ui.layout.CustomFloatingButton
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar
import com.pdmcourse.spotlyfe.data.model.FavoritePlace
import com.pdmcourse.spotlyfe.ui.navigation.AddFavoritePlaceScreenNavigation

@Composable
fun SavedPlacesScreen(navController: NavController) {
  val viewModel: SavedPlacesViewModel = viewModel(factory = SavedPlacesViewModel.Factory)
  val places by viewModel.places.collectAsState(initial = emptyList())

  val initialLocation = if (places.isNotEmpty()) {
    LatLng(places.first().latitude, places.first().longitude)
  } else {
    LatLng(13.681157, -89.235215)
  }

  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(initialLocation, 16f)
  }

  var uiSettings by remember {
    mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
  }
  var properties by remember {
    mutableStateOf(MapProperties(mapType = MapType.HYBRID))
  }

  Scaffold(
    topBar = { CustomTopBar() },
    floatingActionButton = {
      CustomFloatingButton(onClick = {
        navController.navigate(AddFavoritePlaceScreenNavigation)
      })
    }
  ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {
      GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
      ) {
        places.forEach { place: FavoritePlace ->
          Marker(
            state = MarkerState(position = LatLng(place.latitude, place.longitude)),
            title = place.name,
            snippet = place.description
          )
        }
      }
    }
  }
}
