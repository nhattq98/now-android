package com.example.nowandroid.data

import com.example.nowandroid.R

data class Message(
    val content: String,
    val timeStamp: String,
    val isMe: Boolean,
    val avatar: Int = R.drawable.ali,
){
    fun getAuthorName(): String{
        return if(!isMe) "aiBot" else "me"
    }
}