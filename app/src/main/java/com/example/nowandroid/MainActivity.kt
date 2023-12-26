package com.example.nowandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nowandroid.model.ChatCompletions
import com.example.nowandroid.network.RetrofitOpenAINetwork
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: RetrofitOpenAINetwork
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test()
    }

    fun test() {
        getString(R.string.api_key)
        CoroutineScope(Dispatchers.IO).launch {
            val param = ChatCompletions(
                model = "gpt-3.5",
                messages = listOf()
            )
            api.chatCompletions(param)
        }
    }
}