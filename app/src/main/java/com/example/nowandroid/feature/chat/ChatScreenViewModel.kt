package com.example.nowandroid.feature.chat

import androidx.lifecycle.ViewModel
import com.example.nowandroid.data.initialMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState(initialMessages = initialMessage))
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()
}