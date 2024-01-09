package com.example.nowandroid.model

import com.google.gson.annotations.SerializedName

data class ChatCompletionParamRequest (
    @SerializedName("model")
    val model: String = "gpt-3.5-turbo",

    @SerializedName("messages")
    val messages: List<Message>
)