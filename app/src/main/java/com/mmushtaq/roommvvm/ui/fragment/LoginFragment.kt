package com.mmushtaq.roommvvm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mmushtaq.roommvvm.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        initView(view)

        return view
    }

    private fun initView(view: View) {

        val loginBtn = view.findViewById<Button>(R.id.btn_login)
        val etPass = view.findViewById<EditText>(R.id.et_password)
        val etEmail = view.findViewById<EditText>(R.id.et_email)

        loginBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }
}