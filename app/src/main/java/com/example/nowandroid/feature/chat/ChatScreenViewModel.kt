package com.example.nowandroid.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nowandroid.data.MessageItem
import com.example.nowandroid.data.initialMessage
import com.example.nowandroid.model.ChatCompletionParamRequest
import com.example.nowandroid.model.Message
import com.example.nowandroid.reporsitories.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nowandroid.reporsitories.WorkResult
import kotlinx.coroutines.flow.first

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState(messages = initialMessage.toMutableList()))
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun conversation(content: String) {
        _uiState.update {
            it.copy(isLoading = true)
                .addMessage(
                    MessageItem(
                        content = content,
                        timeStamp = System.currentTimeMillis(),
                        isMe = true
                    )
                )
        }

        viewModelScope.launch {
            // fetching
            val result = chatRepository.conversation(
                ChatCompletionParamRequest(
                    messages = listOf(
                        Message(
                            role = "user",
                            content = "hello"
                        )
                    )
                )
            ).first()

            if (result !is WorkResult.Success) {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            } else {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
}