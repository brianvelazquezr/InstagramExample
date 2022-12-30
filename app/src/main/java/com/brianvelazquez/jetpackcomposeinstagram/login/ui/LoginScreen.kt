package com.brianvelazquez.jetpackcomposeinstagram.login.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brianvelazquez.jetpackcomposeinstagram.R

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
    ) {

        val isLoading : Boolean by loginViewModel.isLoading.observeAsState(initial = false)
        
        if(isLoading){
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
        else {
            Header(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp))
            Body(loginViewModel,
                Modifier
                    .align(Alignment.Center)
                    .padding(16.dp))
            Footer(Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFF9F9f9))
        )
        Spacer(modifier = Modifier.height(24.dp))
        SignUp()
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SignUp() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Don't have an account?", fontSize = 12.sp, color = Color(0xFFB5B5B5))
        Text(
            text = "Sign up",
            Modifier.padding(start = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun Body(loginViewModel: LoginViewModel, modifier: Modifier) {

    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnabled: Boolean by loginViewModel.isLoginEnabled.observeAsState(initial = false)

    Column(modifier = modifier) {
        ImageTitle(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        Email(email = email) {
            loginViewModel.onLoginChanged(email = it, password = password)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Password(password = password) {
            loginViewModel.onLoginChanged(email = email, password = it)
        }
        Spacer(modifier = Modifier.height(8.dp))
        ForgotPasswordButton(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.height(16.dp))
        LogInButton(isLoginEnabled, loginViewModel)
        Spacer(modifier = Modifier.height(16.dp))
        LogInDivider()
        Spacer(modifier = Modifier.height(32.dp))
        SocialLogIn()
    }
}

@Composable
fun SocialLogIn() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "log In into Facebook",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continue as Brian Velazquez",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun LogInDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            color = Color(0xFFB5B5B5),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun LogInButton(loginEnable: Boolean, loginViewModel: LoginViewModel) {
    Button(
        onClick = {loginViewModel.isLoginSelected()},
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF4EA8E9),
            disabledBackgroundColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Log In")
    }
}

@Composable
fun ForgotPasswordButton(modifier: Modifier) {
    Text(
        text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
    )
}

@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {

    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = password, onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFF9F9F9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val imagen = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = imagen, contentDescription = "Show password")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Phone number, username or email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFF9F9F9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ImageTitle(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "ImageTitle",
        modifier = modifier
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close Login Screen",
        modifier = modifier.clickable { activity.finish() }
    )
}
