package com.brianvelazquez.jetpackcomposeinstagram.login.data.network

import com.brianvelazquez.jetpackcomposeinstagram.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(email: String, password: String): Boolean{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(LoginClient::class.java).doLogin()
            response.body()?.success ?: false
        }
    }

}