package com.mmushtaq.roommvvm.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.mmushtaq.roommvvm.R
import com.mmushtaq.roommvvm.model.LoginState
import com.mmushtaq.roommvvm.view_model.MainViewModel


class MainFragment : Fragment() {


    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initView(view: View?) {

        val logoTv = view?.findViewById<TextView>(R.id.tv_logo)
        val signOutBtn = view?.findViewById<Button>(R.id.btn_signOut)
        val delBtn = view?.findViewById<Button>(R.id.btn_deleteUser)

        logoTv?.text= """
            You are signed in as ${LoginState.user?.userName}
            """.trimIndent()

            signOutBtn?.setOnClickListener {

                mainViewModel.signOut()
                mainViewModel.onSignedOut.observe(viewLifecycleOwner) {
                    if (it)
                        findNavController(view).navigate(R.id.action_mainFragment_to_loginFragment)

                }

            }


        delBtn?.setOnClickListener {

            onDeleteUser()
            mainViewModel.onDelComplete.observe(viewLifecycleOwner) {
                if (it) {
                    logoTv?.text="Main Class"

                    Toast.makeText(context, "User deleted", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

    private fun onDeleteUser() {
        activity.let {
            AlertDialog.Builder(it)
                .setTitle("Delete User")
                .setMessage("Are you sure you want to delete the user")
                .setPositiveButton("Yes") { _, _ -> run { mainViewModel.onDelUser() } }
                .setNegativeButton("No", null).create().show()
        }
    }

}