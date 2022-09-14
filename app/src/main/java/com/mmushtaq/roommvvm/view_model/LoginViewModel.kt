package com.mmushtaq.roommvvm.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mmushtaq.roommvvm.model.LoginState
import com.mmushtaq.roommvvm.model.User
import com.mmushtaq.roommvvm.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {


    private val coroutineScope= CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }
    val onLoginComplete=MutableLiveData<Boolean>()
    val onError=MutableLiveData<String>()

    fun onLogin(username: String, password: String) {


        coroutineScope.launch {
            val user = db.getUser(username)
            if(user != null && user.passwordHash==password.hashCode())
            {
                withContext(Dispatchers.Main){
                    LoginState.logIn(user)
                    onLoginComplete.value=true
                }
            }
            else {
                withContext(Dispatchers.Main){
                    onError.value="invalid username or password"
                }

            }
        }


    }

}