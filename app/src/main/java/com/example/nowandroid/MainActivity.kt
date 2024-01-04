package com.example.nowandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.nowandroid.feature.chat.ChatScreenContent
import com.example.nowandroid.theme.OpenAIAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        findViewById<ComposeView>(R.id.compose_view).apply {
            setContent {
                OpenAIAppTheme {
                    ChatScreenContent()
                }
            }
        }
    }
}