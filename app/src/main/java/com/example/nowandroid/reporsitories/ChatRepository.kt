package com.example.nowandroid.reporsitories

import com.example.nowandroid.model.ChatCompletionParamRequest
import com.example.nowandroid.model.ChatCompletions
import com.example.nowandroid.network.OpenAINetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface IChatRepository {
    suspend fun conversation(chatCompletions: ChatCompletionParamRequest): Flow<WorkResult<ChatCompletions>>
}

class ChatRepository @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val openAINetworkDataSource: OpenAINetworkDataSource,
) : IChatRepository {
    override suspend fun conversation(chatCompletions: ChatCompletionParamRequest): Flow<WorkResult<ChatCompletions>> {
        return flow {
            emit(
                openAINetworkDataSource
                    .chatCompletions(chatCompletions)
            )
        }.map {
            WorkResult.Success(it)
        }.flowOn(ioDispatcher)
    }
}