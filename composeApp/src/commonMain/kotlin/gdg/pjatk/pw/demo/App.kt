package gdg.pjatk.pw.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import gdg.pjatk.pw.demo.navigation.OnboardingStart
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    MaterialTheme {
        Navigator(OnboardingStart) { nav ->
            FadeTransition(nav) { current -> current.Content() }
        }
    }
}