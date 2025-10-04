package gdg.pjatk.pw.demo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import gdg.pjatk.pw.demo.navigation.CityPicker

@Composable
fun CountryPickerScreen(preselected: String?) {
    val nav = LocalNavigator.currentOrThrow
    var country by remember { mutableStateOf(preselected ?: "PL") }

    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Choose a place that fits you the most.", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        // TODO: map / dropdown
        OutlinedTextField(country, { country = it }, label = { Text("Country code") })
        Spacer(Modifier.height(12.dp))
        Button(onClick = { nav.push(CityPicker(country)) }) { Text("Choose country ▸") }
    }
}