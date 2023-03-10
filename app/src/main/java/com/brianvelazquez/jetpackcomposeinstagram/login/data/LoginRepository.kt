package com.brianvelazquez.jetpackcomposeinstagram.login.data

import android.provider.ContactsContract.CommonDataKinds.Email
import com.brianvelazquez.jetpackcomposeinstagram.login.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api : LoginService){

    suspend fun doLogin(email: String, password: String): Boolean{
        return api.doLogin(email = email, password = password)
    }
}