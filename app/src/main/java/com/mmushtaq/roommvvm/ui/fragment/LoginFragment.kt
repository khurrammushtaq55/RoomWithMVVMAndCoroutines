package com.mmushtaq.roommvvm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mmushtaq.roommvvm.R
import com.mmushtaq.roommvvm.view_model.LoginViewModel

class LoginFragment : Fragment() {


    lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        loginViewModel= ViewModelProvider(this)[LoginViewModel::class.java]

    }
    private fun initView(view: View) {

        val loginBtn = view.findViewById<Button>(R.id.btn_login)
        val signupBtn = view.findViewById<Button>(R.id.btn_Signup)
        val etPass = view.findViewById<EditText>(R.id.et_password)
        val etEmail = view.findViewById<EditText>(R.id.et_email)

        loginBtn.setOnClickListener {
            val email=etEmail.text.toString()
            val password=etPass.text.toString()
            if(email.isEmpty() || password.isEmpty())
            {
                Toast.makeText(context,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            }
            else {

                loginViewModel.onLogin(email, password)
                loginViewModel.onLoginComplete.observe(
                    viewLifecycleOwner,
                    Observer { isComplete: Boolean ->
                        if (isComplete) {
                            Navigation.findNavController(view)
                                .navigate(R.id.action_loginFragment_to_mainFragment)

                        }

                    })
                loginViewModel.onError.observe(viewLifecycleOwner, Observer { error: String ->

                    Toast.makeText(context, "error:$error", Toast.LENGTH_SHORT).show()

                })
            }
        }

        signupBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment)

        }
    }
}