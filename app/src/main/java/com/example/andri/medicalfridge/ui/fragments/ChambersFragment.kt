package com.example.andri.medicalfridge.ui.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andri.medicalfridge.App
import retrofit2.Callback
import retrofit2.Response

import com.example.andri.medicalfridge.R
import com.example.andri.medicalfridge.adapter.ChamberAdapter
import com.example.andri.medicalfridge.model.Fridge
import com.example.andri.medicalfridge.model.MainModel
import com.example.andri.medicalfridge.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_chambers.*
import retrofit2.Call

class ChambersFragment : Fragment(), ChamberAdapter.OnClickListener {

    private lateinit var navController: NavController

    override fun onClick(fridge: Fridge) {
        navController.navigate(R.id.action_chambersFragment_to_medicationsFragment, MedicationsFragment.newInstance(fridge.id))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chambers, container, false)
        setHasOptionsMenu(true)
        val mainActivity: MainActivity = (activity as MainActivity)
        mainActivity.bottomNavigationView.visibility = View.VISIBLE
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        getChambers()
    }

    private fun getChambers() {
        val chambersRequest = App.Api.getFridgesByUserId(App.user.idUser.toInt())
        chambersRequest.enqueue(object : Callback<List<Fridge>> {
            override fun onFailure(call: Call<List<Fridge>>, t: Throwable) {}

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
        rvChambers?.layoutManager = LinearLayoutManager(context)
        context?.let {
            rvChambers?.adapter = ChamberAdapter(it, data, this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_chambers, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_add) {
            createChamber()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createChamber() {
        val createChamberRequest = App.Api.createChamber(MainModel(IdUser = App.user.idUser.toInt()))
        createChamberRequest.enqueue(object : Callback<List<Fridge>> {
            override fun onFailure(call: Call<List<Fridge>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Fridge>>, response: Response<List<Fridge>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        initializeListOfChambers(it)
                    }
                }
            }

        })
    }

}
