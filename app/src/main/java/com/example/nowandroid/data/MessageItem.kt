package com.example.nowandroid.data

import android.content.Context
import com.example.nowandroid.R

data class MessageItem(
    val content: String,
    val timeStamp: Long,
    val isMe: Boolean,
    val avatar: Int = if (isMe) R.drawable.ali else R.drawable.openai_thumb,
) {
    fun getAuthorName(context: Context): String {
        return if (!isMe) context.getString(R.string.aibot) else context.getString(R.string.me)
    }
}