package com.mmushtaq.roommvvm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.mmushtaq.roommvvm.R

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_sign_up, container, false)
        initView(view)

        return view
    }

    private fun initView(view: View) {

        val regBtn=view.findViewById<Button>(R.id.btn_register)
        val etPass=view.findViewById<EditText>(R.id.et_password)
        val etEmail=view.findViewById<EditText>(R.id.et_email)
        val etRePwd=view.findViewById<EditText>(R.id.et_repassword)

        regBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }
}