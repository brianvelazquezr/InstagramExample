package com.brianvelazquez.jetpackcomposeinstagram.login.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log

class LoginService @Inject constructor(private val loginClient: LoginClient){

    //this is now provided by dagger hilt in the NetworkModule
    //private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(email: String, password: String): Boolean{
        return withContext(Dispatchers.IO){
            // This now is injected by dagger hilt in the NetworkModule
            //val response = retrofit.create(LoginClient::class.java).doLogin()
            val response = loginClient.doLogin()
            response.body()?.success ?: false
        }
    }

}