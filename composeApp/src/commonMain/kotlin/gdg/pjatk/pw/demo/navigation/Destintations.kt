package gdg.pjatk.pw.demo.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import gdg.pjatk.pw.demo.screens.AIChatScreen
import gdg.pjatk.pw.demo.screens.BuildingRouteScreen
import gdg.pjatk.pw.demo.screens.CityPickerScreen
import gdg.pjatk.pw.demo.screens.CountryPickerScreen
import gdg.pjatk.pw.demo.screens.MainRouteScreen
import gdg.pjatk.pw.demo.screens.OnboardingStartScreen
import gdg.pjatk.pw.demo.screens.PersonaScreen


object OnboardingStart : Screen {
    @Composable override fun Content() = OnboardingStartScreen()
}

object Persona : Screen {
    @Composable override fun Content() = PersonaScreen()
}

data class CountryPicker(val preselected: String? = null) : Screen {
    @Composable override fun Content() = CountryPickerScreen(preselected)
}

data class CityPicker(val countryCode: String) : Screen {
    @Composable override fun Content() = CityPickerScreen(countryCode)
}

data class PoiMap(
    val cityId: String,
    val personaText: String,
    val tags: List<String>
) : Screen {
    @Composable override fun Content() = MainRouteScreen(cityId, personaText, tags)
}

data class AIChat(val poiId: String) : Screen {
    @Composable override fun Content() = AIChatScreen(poiId)
}

data class RoutePreview(val cityId: String, val poiIds: List<String>) : Screen {
    @Composable override fun Content() = BuildingRouteScreen(cityId, poiIds)
}