package com.example.nowandroid.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nowandroid.data.MessageItem
import com.example.nowandroid.data.Role
import com.example.nowandroid.model.ChatCompletionParamRequest
import com.example.nowandroid.model.Message
import com.example.nowandroid.repositories.ChatRepository
import com.example.nowandroid.repositories.WorkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState(messages = mutableListOf()))
    private val _throwable =
        MutableSharedFlow<Exception>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()
    val throwable: SharedFlow<Exception> = _throwable.asSharedFlow()

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
            chatRepository.conversation(
                ChatCompletionParamRequest(
                    messages = listOf(
                        Message(
                            role = Role.USER.value,
                            content = content
                        )
                    )
                )
            ).collectLatest { result ->
                when (result) {
                    is WorkResult.Success -> {
                        _uiState.update {
                            val contentResult =
                                result.data.choices?.getOrNull(0)?.message?.content ?: ""
                            it.copy(isLoading = false)
                                .addMessage(
                                    MessageItem(
                                        content = contentResult,
                                        timeStamp = System.currentTimeMillis(),
                                        isMe = false
                                    )
                                )
                        }
                    }

                    is WorkResult.Error -> {
                        _uiState.update {
                            it.copy(isLoading = false)
                        }
                        _throwable.emit(result.exception)
                    }
                }
            }
        }
    }
}