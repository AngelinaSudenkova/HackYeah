
package gdg.pjatk.pw.demo.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class CountrySpec(
    val code: String, val name: String,
    val lon: Double, val lat: Double, val zoom: Double,
    val markers: List<MarkerSpec>
)

data class MarkerSpec(
    val id: String,
    val lon: Double,
    val lat: Double
)

data class CountryUiState(
    val countries: List<CountrySpec> = emptyList(),
    val selected: CountrySpec? = null,
    val planet: Boolean = false
)

class CountryViewModel : ScreenModel {
    private val _state = MutableStateFlow(CountryUiState())
    val state: StateFlow<CountryUiState> = _state.asStateFlow()

    init { load() }

    private fun load() = screenModelScope.launch {
        val demo = listOf(
            CountrySpec("PL","Poland",19.1451,51.9194,5.0,
                listOf(MarkerSpec("warsaw",21.0122,52.2297), MarkerSpec("krakow",19.9445,50.0497))),
            CountrySpec("FR","France",2.2137,46.2276,5.0,
                listOf(MarkerSpec("paris",2.3522,48.8566), MarkerSpec("lyon",4.8357,45.7640))),
            CountrySpec("IT","Italy",12.5674,41.8719,5.1,
                listOf(MarkerSpec("rome",12.4964,41.9028), MarkerSpec("milan",9.19,45.4642)))
        )
        _state.value = CountryUiState(countries = demo, selected = demo.first())
    }

    fun select(code: String) {
        val c = _state.value.countries.find { it.code == code } ?: return
        _state.value = _state.value.copy(selected = c)
    }
    fun togglePlanet() {
        _state.value = _state.value.copy(planet = !_state.value.planet)
    }
}
