package com.example.nowandroid.network

import com.example.nowandroid.model.ChatCompletionParamRequest
import com.example.nowandroid.model.ChatCompletions
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton

@Serializable
data class NetworkResponse<T>(
    @SerializedName("data")
    val data: T,
)

private interface RetrofitOpenAINetworkApi {
    @POST("chat/completions")
    suspend fun chatCompletions(@Body chatCompletions: ChatCompletionParamRequest): ChatCompletions
}

@Singleton
class RetrofitOpenAINetwork @Inject constructor(
    okhttpCallFactory: Call.Factory,
) : OpenAINetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl("https://api.openai.com/v1/")
        .callFactory(okhttpCallFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitOpenAINetworkApi::class.java)

    override suspend fun chatCompletions(chatCompletions: ChatCompletionParamRequest): ChatCompletions =
        networkApi.chatCompletions(chatCompletions)
}