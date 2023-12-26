package com.example.nowandroid.data

import android.content.Context
import com.example.nowandroid.R

data class Message(
    val content: String,
    val timeStamp: String,
    val isMe: Boolean,
    val avatar: Int = R.drawable.ali,
){
    fun getAuthorName(context: Context): String{
        return if(!isMe) context.getString(R.string.aibot) else context.getString(R.string.me)
    }
}