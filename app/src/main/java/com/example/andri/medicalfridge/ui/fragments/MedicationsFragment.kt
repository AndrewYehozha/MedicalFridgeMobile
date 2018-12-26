package com.example.andri.medicalfridge.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andri.medicalfridge.App

import com.example.andri.medicalfridge.R
import com.example.andri.medicalfridge.model.Medication
import com.example.andri.medicalfridge.ui.activities.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_medications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMedications()
        val activity = activity as MainActivity
        activity.setToolBarBackButton(true)
    }

    private fun getMedications() {
        val requestMedications = App.Api.getMedications(1000)
        requestMedications.enqueue(object : Callback<List<Medication>> {
            override fun onFailure(call: Call<List<Medication>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Medication>>, response: Response<List<Medication>>) {
                if (response.isSuccessful) {

                }
            }

        })
    }

}
