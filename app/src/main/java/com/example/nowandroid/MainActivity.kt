package com.example.nowandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import com.example.nowandroid.feature.chat.ChatScreenContent


// git access token: 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
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ComposeView>(R.id.compose_view).apply {
            setContent {
//                ChatScreenContent()
            }
        }
    }
}
