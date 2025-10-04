package gdg.pjatk.pw.demo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import gdg.pjatk.pw.demo.navigation.PoiMap

@Composable
fun CityPickerScreen(countryCode: String) {
    val nav = LocalNavigator.currentOrThrow
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Choose city ($countryCode)", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        // TODO: map zoomed to country, pick a city
        Button(onClick = {
            nav.push(PoiMap(cityId = "krakow", personaText = "", tags = emptyList()))
        }) { Text("Open city map") }
    }
}

