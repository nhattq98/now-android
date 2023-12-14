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
                ChatScreenContent()
            }
        }
    }
}
