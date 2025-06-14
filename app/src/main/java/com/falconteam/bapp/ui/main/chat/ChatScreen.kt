package com.falconteam.bapp.ui.main.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.falconteam.bapp.data.models.Mensaje
import kotlinx.datetime.Clock
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDateTime

@Composable
fun ChatScreen(
    conversacionId: String,
    emisorId: String,
    viewModel: ChatViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var messageText by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(conversacionId) {
        viewModel.loadMessages(conversacionId)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else if (uiState.error != null) {
            Text("Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                reverseLayout = true
            ) {
                items(uiState.messages.reversed()) { message ->
                    Text(
                        text = "${message.emisorId.take(5)}: ${message.contenido}",
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Escribe tu mensaje...") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (messageText.text.isNotBlank()) {
                        val mensaje = Mensaje(
                            id = "",
                            conversacionId = conversacionId,
                            emisorId = emisorId,
                            contenido = messageText.text,
                            timestamp = Clock.System.now().toString()
                        )
                        //viewModel.sendMessage(mensaje) agregar logica despues
                        messageText = TextFieldValue("")
                    }
                }) {
                    Text("Enviar")
                }
            }
        }
    }
}
