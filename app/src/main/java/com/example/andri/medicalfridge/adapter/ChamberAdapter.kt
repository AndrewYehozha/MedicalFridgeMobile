package com.example.andri.medicalfridge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.andri.medicalfridge.R
import com.example.andri.medicalfridge.model.Fridge

class ChamberAdapter : RecyclerView.Adapter<ChamberAdapter.ViewHolder> {

    private lateinit var data: List<Fridge>
    private lateinit var context: Context
    private lateinit var listener: OnClickListener

    constructor(context: Context, data: List<Fridge>, listener: OnClickListener) {
        this.context = context
        this.data = data
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chamber, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.tvNumberOfChamber.text = String.format(context.getString(R.string.number_of_chamber), item.id.toString())
        holder.tvTemperature.text = String.format(context.getString(R.string.temperature_in_chamber), item.lastTemperature.toString())
        holder.tvHumidity.text = String.format(context.getString(R.string.humidity_in_chamber), item.lastHumidity.toString())
        holder.tvStateOfChamber.text = if (item.status) context.getString(R.string.state_of_chamber_is_ok) else context.getString(R.string.state_of_chamber_is_fail)
        holder.itemView.setOnClickListener { listener.onClick(item) }
    }

    class ViewHolder : RecyclerView.ViewHolder {

        lateinit var tvNumberOfChamber: TextView
        lateinit var tvTemperature: TextView
        lateinit var tvHumidity: TextView
        lateinit var tvStateOfChamber: TextView

        constructor(view: View): super(view) {
            tvNumberOfChamber = view.findViewById(R.id.tvNumberOfChamber)
            tvTemperature = view.findViewById(R.id.tvTemperature)
            tvHumidity = view.findViewById(R.id.tvHumidity)
            tvStateOfChamber = view.findViewById(R.id.tvStateOfChamber)
        }
    }

    interface OnClickListener {
        fun onClick(fridge: Fridge)
    }
}