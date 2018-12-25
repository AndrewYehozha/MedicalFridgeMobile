package com.example.andri.medicalfridge.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andri.medicalfridge.App
import retrofit2.Callback
import retrofit2.Response

import com.example.andri.medicalfridge.R
import com.example.andri.medicalfridge.adapter.ChamberAdapter
import com.example.andri.medicalfridge.model.Fridge
import com.example.andri.medicalfridge.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_chambers.*
import retrofit2.Call

class ChambersFragment : Fragment(), ChamberAdapter.OnClickListener {

    private lateinit var navController: NavController

    override fun onClick(fridge: Fridge) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chambers, container, false)
        val mainActivity: MainActivity = (activity as MainActivity)
        mainActivity.bottomNavigationView.visibility = View.VISIBLE
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chambersRequest = App.Api.getFridgesByUserId(App.user.idUser)
        chambersRequest.enqueue(object : Callback<List<Fridge>> {
            override fun onFailure(call: Call<List<Fridge>>, t: Throwable) { }

            override fun onResponse(call: Call<List<Fridge>>, response: Response<List<Fridge>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        initializeListOfChambers(it)
                    }
                }
            }
        })
    }

    private fun initializeListOfChambers(data: List<Fridge>) {
        rvChambers.layoutManager = LinearLayoutManager(context)
        context?.let {
            rvChambers.adapter = ChamberAdapter(it, data, this)
        }

    }

}
