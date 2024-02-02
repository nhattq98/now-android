package com.example.nowandroid.repositories

import com.example.nowandroid.model.ChatCompletionParamRequest
import com.example.nowandroid.model.ChatCompletions
import com.example.nowandroid.network.OpenAINetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface IChatRepository {
    suspend fun conversation(chatCompletions: ChatCompletionParamRequest): Flow<WorkResult<ChatCompletions>>
}

class ChatRepository @Inject constructor(
    private val openAINetworkDataSource: OpenAINetworkDataSource,
) : IChatRepository {
    override suspend fun conversation(chatCompletions: ChatCompletionParamRequest): Flow<WorkResult<ChatCompletions>> {
        return flow {
            try {
                val result = openAINetworkDataSource
                    .chatCompletions(chatCompletions)
                emit(WorkResult.Success(result))
            } catch (ex: Exception) {
                emit(WorkResult.Error(ex))
            }

        }.flowOn(Dispatchers.IO)
    }
}