package com.saat.weathertest.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saat.weathertest.Model.Common
import com.saat.weathertest.Model.dataModel.Daily
import com.saat.weathertest.databinding.LayoutDailyAdapterBinding
import java.util.*

class DailyAdapter : RecyclerView.Adapter<ViewHolderDaily>() {

    private var list: List<Daily> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDaily {
        val inflater =  LayoutInflater.from(parent.context)
        val bind = LayoutDailyAdapterBinding.inflate(inflater, parent, false)
        return ViewHolderDaily(bind)
    }

    override fun onBindViewHolder(holder: ViewHolderDaily, position: Int) {
        holder.binding(list[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Daily>){
        this.list = list;
        notifyDataSetChanged()
    }

}
class ViewHolderDaily(var bind: LayoutDailyAdapterBinding) : RecyclerView.ViewHolder(bind.root) {

    fun binding(daily: Daily){
        bind.textViewTempMax.text = getTemp(daily.temp.max)
        bind.textViewTempMin.text = getTemp(daily.temp.min)
        bind.textViewDay.text = getDay(daily.dt.toLong())

        val id = Common.getImageLayout(daily.dt.toLong(),daily.weather[0].main,daily.weather[0].description)
        if(id !== null)
            bind.imageView.setImageResource(id)
    }

    private fun getTemp(temp: Double):String{
        return temp.toString() + "C"
    }

    private fun getDay(long: Long):String{
        val date = Date(long * 1000);
        val calendar = GregorianCalendar()
        calendar.time = date
        return Common.days[calendar.get(Calendar.DAY_OF_WEEK) - 1]
    }
}