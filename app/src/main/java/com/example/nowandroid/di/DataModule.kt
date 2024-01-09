package com.example.nowandroid.di

import com.example.nowandroid.network.OpenAINetworkDataSource
import com.example.nowandroid.network.RetrofitOpenAINetwork
import com.example.nowandroid.reporsitories.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideChatRepository(openAINetwork: OpenAINetworkDataSource): ChatRepository {
        return ChatRepository(openAINetworkDataSource = openAINetwork)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(okhttpCallFactory: Call.Factory): OpenAINetworkDataSource {
        return RetrofitOpenAINetwork(okhttpCallFactory = okhttpCallFactory)
    }
}