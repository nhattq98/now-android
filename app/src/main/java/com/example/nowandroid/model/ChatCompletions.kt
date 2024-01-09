package com.example.nowandroid.model

import com.google.gson.annotations.SerializedName

class ChatCompletions {
    @SerializedName("id")
    val id: String? = null

    @SerializedName("object")
    val objected: String? = null

    @SerializedName("created")
    val created: String? = null

    @SerializedName("model")
    val model: String? = null

    @SerializedName("choices")
    val choices: List<Choices>? = null
}

data class Choices(
    @SerializedName("index")
    val index: Int? = null,

    @SerializedName("message")
    val message: Message? = null,
)

data class Message(
    @SerializedName("role")
    val role: String? = null,

    @SerializedName("content")
    val content: String? = null,
)