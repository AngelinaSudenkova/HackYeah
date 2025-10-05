package gdg.pjatk.pw.demo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import gdg.pjatk.pw.demo.navigation.AIChat
import gdg.pjatk.pw.demo.navigation.RoutePreview
import gdg.pjatk.pw.demo.presentation.MainRouteState
import gdg.pjatk.pw.demo.presentation.MainRouteViewModel

@Composable
fun MainRouteScreen(cityId: String, personaText: String, tags: List<String>) {
    val nav = LocalNavigator.currentOrThrow
    val viewModel = remember { MainRouteViewModel() }
    val state by viewModel.state.collectAsState()


//    when (val s = state) {
//        is MainRouteState.Loading -> Box(Modifier.fillMaxSize()) {
//            CircularProgressIndicator(Modifier.align(Alignment.Center))
//        }
//        is MainRouteState.Ready -> Column(Modifier.fillMaxSize().padding(20.dp)) {
//            Text("City: $cityId")
//            Button(onClick = { nav.push(RoutePreview(cityId, s.pois)) }) {
//                Text("Build a route")
//            }
//            Button(onClick = { nav.push(AIChat(s.pois.first())) }) {
//                Text("Open place details")
//            }
//        }
//        is MainRouteState.Error -> Text("Error: ${s.message}")
//    }
}
