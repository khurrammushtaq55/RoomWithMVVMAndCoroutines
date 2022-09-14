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
import com.mmushtaq.roommvvm.R
import com.mmushtaq.roommvvm.view_model.LoginViewModel
import com.mmushtaq.roommvvm.view_model.SignUpViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {


    lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        signUpViewModel= ViewModelProvider(this)[SignUpViewModel::class.java]

    }
    private fun initView(view: View) {

        val regBtn = view.findViewById<Button>(R.id.btn_register)
        val logBtn = view.findViewById<Button>(R.id.btn_Login)
        val etPass = view.findViewById<EditText>(R.id.et_password)
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etRePwd = view.findViewById<EditText>(R.id.et_repassword)

        regBtn.setOnClickListener {

            val userName = etEmail.text.toString()
            val password = etPass.text.toString()
            val rePassword = etRePwd.text.toString()

            if (userName.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                Toast.makeText(context, "DATA is Empty", Toast.LENGTH_SHORT).show()
            } else if (password != rePassword) {
                Toast.makeText(context, "Password is not same", Toast.LENGTH_SHORT).show()
            } else {
                signUpViewModel.onSignUp(etEmail.text.toString(), etPass.text.toString())

                signUpViewModel.onSignUpComplete.observe(viewLifecycleOwner, Observer {

                    if (it) {
                        Toast.makeText(context, "SignUp Complete", Toast.LENGTH_SHORT).show()

                        Navigation.findNavController(view)
                            .navigate(R.id.action_signUpFragment_to_mainFragment)
                    }

                })
                signUpViewModel.onError.observe(viewLifecycleOwner, Observer {

                    Toast.makeText(context, "Error:$it", Toast.LENGTH_SHORT).show()


                })
            }

        }

        logBtn.setOnClickListener{
            Navigation.findNavController(view)
                .navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }
}