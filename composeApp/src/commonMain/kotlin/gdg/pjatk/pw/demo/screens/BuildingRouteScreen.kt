package gdg.pjatk.pw.demo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BuildingRouteScreen(cityId: String, poiIds: List<String>) {
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        // TODO: draw route over map
        Text("Route in $cityId")
        Text(poiIds.joinToString())
        Spacer(Modifier.height(16.dp))
        Button(onClick = { /* start nav later */ }) { Text("Start route") }
    }
}