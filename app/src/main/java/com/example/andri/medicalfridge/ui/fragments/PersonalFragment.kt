package com.example.andri.medicalfridge.ui.fragments


import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.andri.medicalfridge.App

import com.example.andri.medicalfridge.R
import com.example.andri.medicalfridge.model.User
import kotlinx.android.synthetic.main.fragment_personal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUser()
        btnDone.setOnClickListener { updateUser() }
    }

    private fun initializeUser() {
        val user = App.user
        etEmail?.setText(user.login)
        etAddress?.setText(user.address)
        etCity?.setText(user.city)
        etCountry?.setText(user.country)
        etNameOrganization?.setText(user.nameOrganization)
        etPhone?.setText(user.phone)
    }

    private fun createUserForUpdate() = User(
        App.user.idUser,
        etEmail.text?.toString() ?: App.user.login,
        App.user.password,
        etNameOrganization.text?.toString() ?: App.user.nameOrganization,
        App.user.role,
        etCountry.text?.toString() ?: App.user.country,
        etCity.text?.toString() ?: App.user.city,
        etAddress.text?.toString() ?: App.user.address,
        etPhone.text?.toString() ?: App.user.phone
    )

    private fun updateUser() {
        val users = ArrayList<User>()
        users.add(createUserForUpdate())
        val requestUser = App.Api.updateUser(App.user.idUser.toInt(), createUserForUpdate())
        requestUser.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    response.body()?.let { users ->
                        App.user = users[0]
                        initializeUser()
                    }
                }
            }

        })
    }

}
