
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
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }

            uiState.error != null -> {
                Text("Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    reverseLayout = true,
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(uiState.messages.reversed()) { message ->
                        MessageItem(
                            message = message,
                            isOwnMessage = message.emisorId == "0" //id del usuario
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        placeholder = { Text("Escribe tu mensaje...") },
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            if (textFieldValue.text.isNotBlank()) {
                                val mensaje = Mensaje(
                                    id = "",
                                    conversacionId = "", // ⚠️ Reemplazar por ID real de conversación
                                    emisorId = "",        // ⚠️ Reemplazar por ID del usuario
                                    contenido = textFieldValue.text,
                                    timestamp = Clock.System.now().toString()
                                )

                                coroutineScope.launch {
                                    viewModel.sendMessage(mensaje, "0") // ⚠️ Reemplazar "0"
                                }

                                textFieldValue = TextFieldValue("")
                            }
                        }
                    ) {
                        Text("Enviar")
                    }
                }
            }
        }
    }
}

@Composable
fun MessageItem(message: Mensaje, isOwnMessage: Boolean) {
    val backgroundColor = if (isOwnMessage)
        MaterialTheme.colorScheme.primaryContainer
    else
        MaterialTheme.colorScheme.surfaceVariant

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
