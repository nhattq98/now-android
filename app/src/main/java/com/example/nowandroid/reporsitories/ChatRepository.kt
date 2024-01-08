package com.example.nowandroid.reporsitories

import com.example.nowandroid.model.ChatCompletions
import com.example.nowandroid.model.ChatCompletionsResponse
import com.example.nowandroid.network.OpenAINetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface IChatRepository {
    suspend fun conversation(chatCompletions: ChatCompletions): Flow<ChatCompletionsResponse>
}

class ChatRepository @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    val openAINetworkDataSource: OpenAINetworkDataSource
) : IChatRepository {
    override suspend fun conversation(chatCompletions: ChatCompletions): Flow<ChatCompletionsResponse> {
        return flow {
            emit(
                openAINetworkDataSource
                    .chatCompletions(chatCompletions)
            )
        }.flowOn(ioDispatcher)
    }
}