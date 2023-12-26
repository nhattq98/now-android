package com.example.nowandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nowandroid.feature.chat.ChatScreenContent
import kotlinx.coroutines.CoroutineScope

@Composable
fun ChatNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    NavHost(navController = navController, startDestination = "chatScreen"){
        composable("chatScreen"){
        }
    }
}