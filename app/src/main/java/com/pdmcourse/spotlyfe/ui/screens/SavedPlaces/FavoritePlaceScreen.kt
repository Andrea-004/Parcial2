package com.pdmcourse.spotlyfe.ui.screens.SavedPlaces

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFavoritePlaceScreen(navController: NavController, onSave: (String, String?, LatLng) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var selectedPosition by remember {
        mutableStateOf(LatLng(13.681157, -89.235215))
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(selectedPosition, 16f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Place") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    if (name.text.isNotBlank()) {
                        onSave(name.text, description.text.takeIf { it.isNotBlank() }, selectedPosition)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Save")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                cameraPositionState = cameraPositionState,
                onMapClick = { latLng ->
                    selectedPosition = latLng
                }
            ) {
                Marker(state = MarkerState(position = selectedPosition))
            }
        }
    }
}
