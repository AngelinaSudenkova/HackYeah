package gdg.pjatk.pw.demo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import gdg.pjatk.pw.demo.presentation.AIChatViewModel

@Composable
fun AIChatScreen(poiId: String) {
    val nav = LocalNavigator.currentOrThrow
    val viewModel = remember { AIChatViewModel() }
    val messages by viewModel.messages.collectAsState()
    var question by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedButton(onClick = { nav.pop() }) { Text("Back") }
        Text("POI: $poiId", style = MaterialTheme.typography.titleLarge)

        LazyColumn(Modifier.weight(1f)) {
            items(messages) { msg ->
                Text(
                    msg.text,
                    color = if (msg.fromUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }
        }

        OutlinedTextField(
            value = question,
            onValueChange = { question = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ask your question") }
        )
        Button(
            onClick = {
                viewModel.send(question)
                question = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Send") }
    }
}
