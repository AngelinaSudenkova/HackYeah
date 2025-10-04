package gdg.pjatk.pw.demo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import gdg.pjatk.pw.demo.navigation.Persona

@Composable
fun OnboardingStartScreen() {
    val nav = LocalNavigator.currentOrThrow
    Box(Modifier.fillMaxSize()) {
        // TODO: your Earth hero + headings
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { nav.push(Persona) }
        ) { Text("Start exploring") }
    }
}