package com.saat.weathertest.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saat.weathertest.Model.Common
import com.saat.weathertest.Model.dataModel.Hourly
import com.saat.weathertest.databinding.LayoutHourlyAdapterBinding
import java.text.SimpleDateFormat
import java.util.*

class HourlyAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var list: List<Hourly> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        val bind = LayoutHourlyAdapterBinding.inflate(inflater,parent,false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(list[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Hourly>){
        this.list = list
        notifyDataSetChanged()
    }

}

class ViewHolder( var bind: LayoutHourlyAdapterBinding) : RecyclerView.ViewHolder(bind.root) {

    fun binding(hourly: Hourly){

        bind.textViewTemp.text = getTemp(hourly.temp)
        bind.textViewTime.text = getTime(hourly.dt)
        val id = Common.getImageLayout(hourly.dt,hourly.weather[0].main,hourly.weather[0].description)
        if(id !== null)
            bind.imageView.setImageResource(id)
        // TODO: 11/6/2020 not implemented
    }

    private fun getTemp(temp : Double):String{
        return temp.toString() + "C"
    }

    private fun getTime(long: Long):String{
        val simpleDateFormat = SimpleDateFormat("hh aa", Locale.getDefault())
        return simpleDateFormat.format(Date(long*1000))
    }
}