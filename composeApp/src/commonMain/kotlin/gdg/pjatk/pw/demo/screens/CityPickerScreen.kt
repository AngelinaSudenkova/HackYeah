// commonMain/gdg/pjatk/pw/demo/screens/CityPickerScreen.kt
package gdg.pjatk.pw.demo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import gdg.pjatk.pw.demo.CityMap
import gdg.pjatk.pw.demo.MarkerSpec
import gdg.pjatk.pw.demo.data.Countries
import gdg.pjatk.pw.demo.data.CitySpec
import gdg.pjatk.pw.demo.navigation.PoiMap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityPickerScreen(countryCode: String) {
    val nav = LocalNavigator.currentOrThrow
    val country = remember(countryCode) { Countries[countryCode] }

    if (country == null) {
        Box(Modifier.fillMaxSize().background(Color(0xFF071B29)), Alignment.Center) {
            Text("Unknown country: $countryCode", color = Color.White)
        }
        return
    }

    var selectedCity by remember { mutableStateOf(country.cities.first()) }
    var expanded by remember { mutableStateOf(false) }

    // Page background (same night tone as your other screens)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF071B29))
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Back (simple, light text—no default AppBar)
        TextButton(onClick = { nav.pop() }, contentPadding = PaddingValues(0.dp)) {
            Text("← Back", color = Color(0xCCFFFFFF), fontSize = 16.sp)
        }

        Spacer(Modifier.height(8.dp))

        // Big title like the rest of the app
        Text(
            text = "Explore ${country.name}",
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )

        Spacer(Modifier.height(16.dp))

        // Glassy dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selectedCity.name,
                onValueChange = {},
                readOnly = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                label = { Text("Choose city") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0x22FFFFFF),
                    unfocusedContainerColor = Color(0x1AFFFFFF),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color(0xE6FFFFFF),
                    unfocusedLabelColor = Color(0xCCFFFFFF),
                    cursorColor = Color.White
                )
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                country.cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(city.name) },
                        onClick = {
                            selectedCity = city
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Map in a rounded card with a soft border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .border(1.dp, Color(0x26FFFFFF), RoundedCornerShape(24.dp))
        ) {
            CityMap(
                modifier = Modifier.fillMaxSize(),
                centerLon = selectedCity.lon,
                centerLat = selectedCity.lat,
                zoom = selectedCity.zoom,
                planet = false,
                markers = country.cities.map { it.toMarker() },
                onMarkerTap = { id ->
                    country.cities.firstOrNull { it.id == id }?.let { selectedCity = it }
                }
            )

            // Soft gradient at top to blend controls over the map
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .align(Alignment.TopCenter)
                    .background(
                        Brush.verticalGradient(
                            0f to Color(0xFF071B29),
                            0.6f to Color(0x00071B29),
                            1f to Color.Transparent
                        )
                    )
            )

            // Floating pill button like in your mockups
            Button(
                onClick = {
                    nav.push(
                        PoiMap(
                            cityId = selectedCity.id,
                            personaText = "",
                            tags = emptyList()
                        )
                    )
                },
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF101114)
                ),
                contentPadding = PaddingValues(horizontal = 22.dp, vertical = 12.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Text("Open ${selectedCity.name}", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

private fun CitySpec.toMarker() = MarkerSpec(id = id, lon = lon, lat = lat)
