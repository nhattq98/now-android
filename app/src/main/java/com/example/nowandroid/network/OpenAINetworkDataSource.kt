package com.example.nowandroid.network

import com.example.nowandroid.model.ChatCompletionParamRequest
import com.example.nowandroid.model.ChatCompletions

interface OpenAINetworkDataSource {
    suspend fun chatCompletions(chatCompletions: ChatCompletionParamRequest): ChatCompletions
}