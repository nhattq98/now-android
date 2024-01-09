package com.example.nowandroid.data

val initialMessage = listOf(
    MessageItem(
        content = "Hello Ai Chat",
        timeStamp = System.currentTimeMillis(),
        isMe = true,
    ),
    MessageItem(
        content = "Hello , i am GPT 4",
        timeStamp = System.currentTimeMillis(),
        isMe = false
    )
)