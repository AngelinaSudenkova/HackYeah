package gdg.pjatk.pw.demo.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class MainRouteState {
    object Loading : MainRouteState()
    data class Ready(val pois: List<String>) : MainRouteState()
    data class Error(val message: String) : MainRouteState()
}

class MainRouteViewModel : ScreenModel {
    private val _state = MutableStateFlow<MainRouteState>(MainRouteState.Loading)
    val state = _state.asStateFlow()

    init {
        loadPois()
    }

    private fun loadPois() {
        // fake async load
        MainScope().launch {
            delay(1000)
            _state.value = MainRouteState.Ready(listOf("poi-1", "poi-2", "poi-3"))
        }
    }
}
