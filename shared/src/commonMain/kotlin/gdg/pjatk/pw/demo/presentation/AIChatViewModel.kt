package gdg.pjatk.pw.demo.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChatMessage(val fromUser: Boolean, val text: String)

class AIChatViewModel : ScreenModel {


    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    fun send(question: String) {
        MainScope().launch {
            _messages.update { it + ChatMessage(true, question) }
            delay(500)
            _messages.update { it + ChatMessage(false, "This place was built in 2014!") }
        }
    }
}
