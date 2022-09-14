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

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }
    val onSignedOut = MutableLiveData<Boolean>()
    val onDelComplete = MutableLiveData<Boolean>()


    fun signOut() {

        LoginState.logout()
        onSignedOut.value = true

    }

    fun onDelUser() {


        coroutineScope.launch {
            LoginState.user?.let { db.deleteUser(it.id) }
            withContext(Dispatchers.Main) {
               LoginState.logout()
                onDelComplete.value = true
            }
                    }
    }


}

