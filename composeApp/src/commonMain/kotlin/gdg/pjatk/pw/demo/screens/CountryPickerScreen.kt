package gdg.pjatk.pw.demo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import gdg.pjatk.pw.demo.CityMap

import gdg.pjatk.pw.demo.navigation.CityPicker
 import gdg.pjatk.pw.demo.navigation.PoiMap

data class CountrySpec(
    val code: String,
    val name: String,
    val lon: Double,
    val lat: Double,
    val zoom: Double = 5.0
)

private val countries = listOf(
    CountrySpec("PL", "Poland",     19.1451, 51.9194, 5.0),
    CountrySpec("DE", "Germany",    10.4515, 51.1657, 5.0),
    CountrySpec("FR", "France",      2.2137, 46.2276, 5.0),
    CountrySpec("IT", "Italy",      12.5674, 41.8719, 5.1),
    CountrySpec("ES", "Spain",      -3.7492, 40.4637, 5.0)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerScreen(preselected: String? = null) {
    val nav = LocalNavigator.currentOrThrow

    val initial = countries.find { it.code == preselected } ?: countries.first()
    var selected by remember { mutableStateOf(initial) }
    var expanded by remember { mutableStateOf(false) }

    val buttonContainer = Color(0xFFFFFFFF)
    val buttonContent = Color(0xFF000000)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF071B29))
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        // Title
        Text(
            "Choose a place that\nfits you the most.",
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )

        Spacer(Modifier.height(20.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selected.name,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                label = { Text("Choose country") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0x33FFFFFF),
                    unfocusedContainerColor = Color(0x22FFFFFF),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color(0xCCFFFFFF),
                    unfocusedLabelColor = Color(0xAAFFFFFF),
                    cursorColor = Color.White
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text(country.name) },
                        onClick = {
                            selected = country
                            expanded = false
                            // nav.push(CityPicker(country.code))
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(10.dp))
        Divider(color = Color(0x33FFFFFF))
        Spacer(Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            CityMap(
                modifier = Modifier.fillMaxSize(),
                centerLon = selected.lon,
                centerLat = selected.lat,
                zoom = selected.zoom,
                onMarkerTap = { poiId ->
                     nav.push(PoiMap(cityId = poiId, personaText = "", tags = emptyList()))
                }
            )


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
        }


         Spacer(Modifier.height(12.dp))
         Button(
             onClick = { nav.push(CityPicker(selected.code)) },
             modifier = Modifier.fillMaxWidth(),
             colors = ButtonDefaults.buttonColors(
                 containerColor = buttonContainer,
                 contentColor = buttonContent
             ),
         ) { Text("Continue") }
    }
}

