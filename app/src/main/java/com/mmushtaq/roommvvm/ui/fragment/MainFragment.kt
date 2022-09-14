package com.mmushtaq.roommvvm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.mmushtaq.roommvvm.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View?) {

        val loginBtn = view?.findViewById<Button>(R.id.btn_login)
        val delBtn = view?.findViewById<Button>(R.id.btn_deleteUser)

        loginBtn?.setOnClickListener {
            findNavController(view).navigate(R.id.action_mainFragment_to_loginFragment)

        }

        delBtn?.setOnClickListener {
            findNavController(view).navigate(R.id.action_mainFragment_to_signUpFragment)

        }
    }

}