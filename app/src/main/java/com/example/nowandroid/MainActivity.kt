package com.example.nowandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView


// git access token: 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
class MainActivity : AppCompatActivity() {
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