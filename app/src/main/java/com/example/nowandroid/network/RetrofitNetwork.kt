package com.example.nowandroid.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


private interface RetrofitOpenAINetworkApi {

}

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)


@Singleton
class RetrofitOpenAINetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : RetrofitOpenAINetworkApi {

    private val baseUrl = ""
    private val networkApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitOpenAINetworkApi::class.java)
}