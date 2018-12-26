package com.example.andri.medicalfridge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.andri.medicalfridge.R
import com.example.andri.medicalfridge.model.Medication
import com.google.android.material.button.MaterialButton

class MedicationAdapter : RecyclerView.Adapter<MedicationAdapter.ViewHolder> {

    private lateinit var data: List<Medication>
    private lateinit var context: Context
    private lateinit var listener: OnClickListener

    constructor(data: List<Medication>, context: Context?, listener: OnClickListener) {
        this.data = data
        this.listener = listener
        context?.let {
            this.context = it
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medication, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.tvNameOfMedication.text = String.format(context.getString(R.string.name_of_medication), item.name)
        holder.tvAmount.text = String.format(context.getString(R.string.amount), item.amount.toString())
        holder.tvDateOfCreate.text = String.format(
            context.getString(R.string.date_of_create),
            item.dateProduction
        )
        holder.tvDateOfExpiration.text = String.format(context.getString(R.string.date_of_expiration), item.expirationDate)
        holder.tvPrice.text = String.format(context.getString(R.string.price), item.price.toString())
        holder.tvStatus.text = if (item.status) context.getString(R.string.status_ok) else context.getString(R.string.status_fail)
        holder.tvMinTemperature.text = String.format(context.getString(R.string.min_temperature), item.minTemperature.toString())
        holder.tvMaxTemperature.text = String.format(context.getString(R.string.max_temperature), item.maxTemperature.toString())
        holder.btnDelete.setOnClickListener { listener.deleteMedication(item) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNameOfMedication = view.findViewById<TextView>(R.id.tvNameOfMedication)
        val tvAmount = view.findViewById<TextView>(R.id.tvAmount)
        val tvDateOfCreate = view.findViewById<TextView>(R.id.tvDateOfCreate)
        val tvDateOfExpiration = view.findViewById<TextView>(R.id.tvDateOfExpiration)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val tvStatus = view.findViewById<TextView>(R.id.tvStatus)
        val tvMinTemperature = view.findViewById<TextView>(R.id.tvMinTemperature)
        val tvMaxTemperature = view.findViewById<TextView>(R.id.tvMaxTemperature)
        val btnDelete = view.findViewById<MaterialButton>(R.id.btnDelete)
    }

    interface OnClickListener {
        fun deleteMedication(medication: Medication)
    }
}