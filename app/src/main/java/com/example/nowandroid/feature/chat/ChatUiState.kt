package com.example.nowandroid.feature.chat

import com.example.nowandroid.data.MessageItem

data class ChatUiState(
    val messages: MutableList<MessageItem>,
    var isLoading: Boolean = false,
) {
    fun addMessage(msg: MessageItem): ChatUiState {
        messages.add(0, msg)
        return this
    }
}