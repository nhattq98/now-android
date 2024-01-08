package com.example.nowandroid.feature.chat

import com.example.nowandroid.data.Message

data class ChatUiState(
    val messages: MutableList<Message>,
    var isLoading: Boolean = false,
) {
    fun addMessage(msg: Message): ChatUiState{
        messages.add(0, msg)
        return this
    }
}