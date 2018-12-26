package com.example.andri.medicalfridge.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andri.medicalfridge.model.Medication

class MedicationAdapter : RecyclerView.Adapter<MedicationAdapter.ViewHolder> {

    private lateinit var data: List<Medication>
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    constructor(data: List<Medication>, context: Context?) {
        this.data = data
        context?.let {
            this.context = it
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {



    }
}