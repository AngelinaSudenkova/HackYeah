package gdg.pjatk.pw.demo.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import gdg.pjatk.pw.demo.navigation.CountryPicker
import gdg.pjatk.pw.demo.presentation.PersonaViewModel

@Composable
fun PersonaScreen() {
    val nav = LocalNavigator.currentOrThrow
    val viewModel = remember { PersonaViewModel() }
    val state by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Our AI will find the best option for you.")

        FlowRow {
            val tags = listOf("Solo","Couple","Family","Tech","Relaxing","Active","Culture")
            tags.forEach { tag ->
                FilterChip(
                    selected = tag in state.tags,
                    onClick = { viewModel.toggleTag(tag) },
                    label = { Text(tag) }
                )
            }
        }

        OutlinedTextField(
            value = state.about,
            onValueChange = { viewModel.updateAbout(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Tell us about yourself") }
        )

        Spacer(Modifier.height(20.dp))
        Button(onClick = { nav.push(CountryPicker()) }) { Text("Explore") }
    }
}
