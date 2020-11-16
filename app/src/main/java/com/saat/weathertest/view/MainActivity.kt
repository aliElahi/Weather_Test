package com.saat.weathertest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.saat.weathertest.App
import com.saat.weathertest.Model.Common
import com.saat.weathertest.Model.DaySplit
import com.saat.weathertest.R
import com.saat.weathertest.databinding.ActivityMainBinding
import com.saat.weathertest.view.adapters.DailyAdapter
import com.saat.weathertest.view.adapters.HourlyAdapter
import com.saat.weathertest.viewModel.MainActivityViewModel
import com.saat.weathertest.viewModel.ViewModelFactory
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory :ViewModelFactory
    @Inject
    lateinit var hourlyAdapter: HourlyAdapter
    @Inject
    lateinit var dailyAdapter: DailyAdapter
    @Inject
    @Named("horizontal")
    lateinit var hourlyLayoutManager: LinearLayoutManager
    @Inject
    @Named("vertical")
    lateinit var dailyLayoutManager: LinearLayoutManager

    private lateinit var viewModel:MainActivityViewModel
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(application is App){
            (application as App).applicationComponent.inject(this)
        }


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setBackGround()

        binding!!.recyclerViewHourly.layoutManager = hourlyLayoutManager
        binding!!.recyclerViewDaily.layoutManager = dailyLayoutManager

        binding!!.recyclerViewDaily.adapter = dailyAdapter
        binding!!.recyclerViewHourly.adapter = hourlyAdapter


        viewModel = viewModelFactory.create(MainActivityViewModel::class.java)

        viewModel.getDataLiveData().observe(this,
            {
                dailyAdapter.setList(it.daily)
                hourlyAdapter.setList(it.hourly)

                binding!!.textViewLoc.text = getCityName(it.timezone)

                val s = it.current.temp.toString() +"C"+ "/" + it.current.feels_like.toString() + "C"
                binding!!.textViewMaxMinTemp.text = s

                binding!!.textViewTemp.text = it.current.temp.toString()

                val id = Common.getImageLayout(it.current.dt.toLong()
                    ,it.current.weather[0].main,it.current.weather[0].description)
                if (id != null)
                binding!!.imageViewCurrent.setImageResource(id)


                /*TODO("Not yet implemented")*/
            })

        val tehran = viewModel.getWeatherLiveData().find { citiesModelItem -> citiesModelItem.name == "Tehran" }
        //viewModel.getWeather("33.441792","-94.037689")
        viewModel.getWeather(tehran!!.coord.lat.toString(), tehran.coord.lon.toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.search){
            SimpleSearchDialogCompat(this,
                "Search",
                null,
                null,
                (ArrayList(viewModel.getWeatherLiveData())),
                SearchResultListener { dialog, item, position ->
                    if (item != null) {
                        viewModel.getWeather(item.coord.lat.toString(), item.coord.lon.toString())
                        dialog.dismiss()
                    }
                }).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun getBackGroundResource():Int?{
       /* val date = Date(System.currentTimeMillis())
        val calendar = GregorianCalendar()
        calendar.time = date

        val hour = calendar.get(Calendar.HOUR_OF_DAY)*/

        return when(Common.getTimeSplit(System.currentTimeMillis())){
            DaySplit.Dawn -> R.drawable.dawn_resource
            DaySplit.Morning -> R.drawable.morning_resource
            DaySplit.Noon -> R.drawable.noon_resource
            DaySplit.Evening -> R.drawable.evening_resources
            DaySplit.Night -> R.drawable.night_resource
            else -> {
                return null
            }
        }

     /*   if(hour in 0..6)
            return R.drawable.dawn_resource
        else if (hour in 7..11)
            return R.drawable.morning_resource
        else if (hour in 12..16)
            return R.drawable.noon_resource
        else if (hour in 17..19)
            return R.drawable.evening_resources
        else if(hour in 20..23)
            return R.drawable.night_resource

        return null*/
    }

    private fun setBackGround(){
        val id = getBackGroundResource()
        if(id != null)
            binding?.root?.setBackgroundResource(id)
    }

    private fun getCityName(name:String):String{
        val index = name.indexOf('/')
        return if(index == -1)
            name
        else
            name.substring(index + 1,name.length)
    }
}