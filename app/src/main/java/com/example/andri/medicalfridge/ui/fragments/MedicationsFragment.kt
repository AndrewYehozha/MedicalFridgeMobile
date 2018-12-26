package com.example.andri.medicalfridge.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andri.medicalfridge.App

import com.example.andri.medicalfridge.R
import com.example.andri.medicalfridge.adapter.MedicationAdapter
import com.example.andri.medicalfridge.model.Medication
import com.example.andri.medicalfridge.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_medications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicationsFragment : Fragment(), MedicationAdapter.OnClickListener {
    override fun deleteMedication(medication: Medication) {
        deleteMedication(medication.idMedication)
    }

    private var idChamber: Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idChamber = arguments?.getInt(ARG_ID_CHAMBER) ?: 1000
    }

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
        val requestMedications = App.Api.getMedications(idChamber)
        requestMedications.enqueue(object : Callback<List<Medication>> {
            override fun onFailure(call: Call<List<Medication>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Medication>>, response: Response<List<Medication>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        initializeMedicationsList(it)
                    }
                }
            }

        })
    }

    private fun deleteMedication(idMedication: Int) {
        val requestMedication = App.Api.deleteMedications(idMedication)
        requestMedication.enqueue(object : Callback<List<Medication>> {
            override fun onFailure(call: Call<List<Medication>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Medication>>, response: Response<List<Medication>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        initializeMedicationsList(it)
                    }
                }
            }

        })
    }

    private fun initializeMedicationsList(data: List<Medication>) {
        rvMedications?.layoutManager = LinearLayoutManager(context)
        rvMedications?.adapter = MedicationAdapter(data, context, this)
    }

    companion object {
        private const val ARG_ID_CHAMBER = "arg_id_chamber"
        fun newInstance(idChamber: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(ARG_ID_CHAMBER, idChamber)
            return bundle
        }
    }

}
