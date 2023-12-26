package com.example.nowandroid.model

import com.google.gson.annotations.SerializedName

data class ChatCompletions(
    @SerializedName("model")
    val model: String? = null,
    @SerializedName("messages")
    val messages: List<Messages>? = null
)

data class Messages(
    @SerializedName("role")
    val role: String? = null,

    @SerializedName("content")
    val content: String? = null
)