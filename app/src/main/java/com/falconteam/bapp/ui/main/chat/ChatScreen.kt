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
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var messageText by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()

    // Cargar mensajes cuando cambia el ID de la conversación
    /*LaunchedEffect(conversacionId) {
        viewModel.loadMessages(conversacionId)
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else if (uiState.error != null) {
            Text("Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                reverseLayout = true,
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(uiState.messages.reversed()) { message ->
                    MessageItem(message = message, isOwnMessage = message.emisorId == "0") // TODO: Cambia "0" por el ID del usuario actual)
                }
            }

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Escribe tu mensaje...") }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (messageText.text.isNotBlank()) {
                            val mensaje = Mensaje(
                                id = "",
                                conversacionId = "", // TODO: Cambia esto por el ID de la conversación actual
                                emisorId = "", // TODO: Cambia esto por el ID del usuario actual
                                contenido = messageText.text,
                                timestamp = Clock.System.now().toString()
                            )

                            coroutineScope.launch {
                                viewModel.sendMessage(mensaje, 0.toString())
                            }

                            messageText = TextFieldValue("")
                        }
                    }
                ) {
                    Text("Enviar")
                }
            }
        }
    }
}

@Composable
fun MessageItem(message: Mensaje, isOwnMessage: Boolean) {
    val backgroundColor = if (isOwnMessage) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
    val alignment = if (isOwnMessage) Arrangement.End else Arrangement.Start

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = alignment
    ) {
        Surface(
            color = backgroundColor,
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 2.dp
        ) {
            Text(
                text = message.contenido,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
