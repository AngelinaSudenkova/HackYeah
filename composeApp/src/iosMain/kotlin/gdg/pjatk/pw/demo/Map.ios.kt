package gdg.pjatk.pw.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
actual fun CityMap(
    modifier: Modifier,
    centerLon: Double,
    centerLat: Double,
    zoom: Double,
    planet: Boolean,
    markers: List<MarkerSpec>,
    onMarkerTap: (id: String) -> Unit
) {
}