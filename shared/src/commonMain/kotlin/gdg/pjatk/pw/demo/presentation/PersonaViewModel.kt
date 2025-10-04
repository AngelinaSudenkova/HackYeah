package gdg.pjatk.pw.demo.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class PersonaViewModel : ScreenModel {
    private val _state = MutableStateFlow(PersonaUiState())
    val state: StateFlow<PersonaUiState> = _state.asStateFlow()

    fun toggleTag(tag: String) {
        _state.update { it.copy(tags = it.tags.toggle(tag)) }
    }

    fun updateAbout(text: String) {
        _state.update { it.copy(about = text) }
    }
}

private fun <T> Set<T>.toggle(item: T): Set<T> =
    if (contains(item)) minus(item) else plus(item)


data class PersonaUiState(
    val tags: Set<String> = emptySet(),
    val about: String = ""
)
