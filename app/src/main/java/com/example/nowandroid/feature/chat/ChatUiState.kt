package com.example.nowandroid.feature.chat

import androidx.compose.runtime.toMutableStateList
import com.example.nowandroid.data.Message

class ChatUiState(
    val initialMessages: List<Message>
) {
    private val _messages: MutableList<Message> = initialMessages.toMutableStateList()
    val messages: List<Message> = _messages

    fun addMessage(msg: Message){
        _messages.add(0, msg)
    }
}