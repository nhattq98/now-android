package com.example.nowandroid.feature.chat

import androidx.compose.runtime.toMutableStateList
import com.example.nowandroid.data.Message

class MessageUiState(
    initialMessages: List<Message>
) {
    private val _messages: MutableList<Message> = initialMessages.toMutableStateList()
    val messages: List<Message> = _messages
}