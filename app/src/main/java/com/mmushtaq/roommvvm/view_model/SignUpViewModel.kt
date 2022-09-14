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

class SignUpViewModel(application: Application) : AndroidViewModel(application) {


    private val coroutineScope= CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }
    val onSignUpComplete=MutableLiveData<Boolean>()
    val onError=MutableLiveData<String>()

    fun onSignUp(username: String, password: String) {


        coroutineScope.launch {
            val user = db.getUser(username)
            if(user != null)
            {
                withContext(Dispatchers.Main){
                    onError.value="user already existed"
                }
            }
            else {
                val userObj=User(username,password.hashCode())
                val userid=db.insertUser(userObj)
                userObj.id=userid
                LoginState.logIn(userObj)

                withContext(Dispatchers.Main){
                    onSignUpComplete.value=true
                }

            }
        }


    }

}