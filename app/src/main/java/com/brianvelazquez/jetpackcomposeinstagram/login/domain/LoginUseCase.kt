package com.brianvelazquez.jetpackcomposeinstagram.login.domain

import android.provider.ContactsContract.CommonDataKinds.Email
import com.brianvelazquez.jetpackcomposeinstagram.login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository : LoginRepository){

    suspend operator fun invoke(email: String, password: String): Boolean {
        return repository.doLogin(email, password)
    }

}