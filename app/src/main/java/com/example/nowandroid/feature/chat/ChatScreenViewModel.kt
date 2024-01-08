package com.example.nowandroid.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nowandroid.data.Message
import com.example.nowandroid.data.initialMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState(messages = initialMessage.toMutableList()))
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun conversation(content: String) {
        _uiState.update {
            it.copy(isLoading = true)
                .addMessage(
                    Message(
                        content = content,
                        timeStamp = System.currentTimeMillis(),
                        isMe = true
                    )
                )
        }

        viewModelScope.launch {
            // fetching

            delay(2500)
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}