package gdg.pjatk.pw.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class MarkerSpec(
    val id: String,
    val lon: Double,
    val lat: Double
)

@Composable
expect fun CityMap(
    modifier: Modifier = Modifier,
    centerLon: Double = 10.0,
    centerLat: Double = 50.0,
    zoom: Double = 3.6,
    planet: Boolean = false,
    markers: List<MarkerSpec> = emptyList(),
    onMarkerTap: (id: String) -> Unit = {}
)