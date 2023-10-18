package com.example.nowandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView


// git access token: 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
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ComposeView>(R.id.compose_view).apply {
            setContent {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Surface {
        Text(text = "Hello Compose")
    }
}