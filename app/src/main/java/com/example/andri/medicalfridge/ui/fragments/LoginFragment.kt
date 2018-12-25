package com.example.andri.medicalfridge.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.andri.medicalfridge.App

import com.example.andri.medicalfridge.R
import com.example.andri.medicalfridge.model.User
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var mNavComponent: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavComponent = Navigation.findNavController(view)
        btnLogin.setOnClickListener { loginUser() }
    }

    private fun loginUser() {
        val email = etEmail.text?.toString()
        val password = etPassword.text?.toString()
        if (email?.isNotEmpty() == true && password?.isNotEmpty() == true) {
            val userRequest: Call<List<User>> = App.Api.loginUser(email, password)
            userRequest.enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {}

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                        App.user = response.body()!![0]
                        mNavComponent.navigate(LoginFragmentDirections.actionLoginFragmentToChambersFragment())
                    }
                }

            })
        }
    }

}
