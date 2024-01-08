package com.example.nowandroid.network

import com.example.nowandroid.model.ChatCompletions
import com.example.nowandroid.model.ChatCompletionsResponse

interface OpenAINetworkDataSource {
    suspend fun chatCompletions(chatCompletions: ChatCompletions): ChatCompletionsResponse
}