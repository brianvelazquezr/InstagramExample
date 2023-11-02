package com.brianvelazquez.jetpackcomposeinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.brianvelazquez.jetpackcomposeinstagram.login.ui.LoginViewModel
import com.brianvelazquez.jetpackcomposeinstagram.login.ui.LoginScreen
import com.brianvelazquez.jetpackcomposeinstagram.ui.theme.JetpackComposeinstagramTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val loginViewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeinstagramTheme {
                // A surface container using the 'background' color from the theme
                // Just a comment
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen(loginViewModel)
                }
            }
        }
    }
}

