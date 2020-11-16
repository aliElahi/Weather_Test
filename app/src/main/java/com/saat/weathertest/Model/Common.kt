package com.saat.weathertest.Model

import com.saat.weathertest.R
import java.util.*

class Common{

     companion object {
        const val KEY = "851aae0c6f8ee22e5d1ec72820efcceb"
        const val JSON_CITIES_FILE_NAME = "city.list.json"
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val API_UNIT = "metric"
        val days = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

         fun getImageLayout(time: Long ,weatherName:String ,weatherDetail:String):Int?{
             when(weatherName){
                 "Snow" -> return R.drawable.ic___snow
                 "Clouds" ->{

                     if(weatherDetail == "scattered clouds")
                         return R.drawable.ic___scattered_clouds
                     else if (weatherDetail == "broken clouds")
                         return R.drawable.ic___broken_cloud

                     return when(getTimeSplit(time)){
                         DaySplit.Dawn -> R.drawable.ic___few_cloud_dawn
                         DaySplit.Night -> R.drawable.ic___few_clound_night
                         DaySplit.Evening -> R.drawable.ic___few_cloud_evening
                         DaySplit.Morning -> return R.drawable.ic___few_cloud_morning
                         DaySplit.Noon -> return R.drawable.ic___few_cloud_morning // not in resources
                         else -> {
                             null
                         }
                     }
                 }
                 "Thunderstorm" -> return R.drawable.ic____thunderstorm
                 "Rain"->{
                     return if(weatherDetail == "shower rain")
                         R.drawable.ic___shower_raint
                     else
                         R.drawable.ic___rain
                 }
                 "Mist" -> return R.drawable.ic___mist
                 "Clear"->{
                     return when(getTimeSplit(time)){
                         DaySplit.Dawn -> R.drawable.ic___clear_sky_dawn
                         DaySplit.Night -> R.drawable.ic___clear_sky_night
                         DaySplit.Evening -> R.drawable.ic___clear_sky_evening
                         DaySplit.Morning -> return R.drawable.ic___clear_sky_morning
                         DaySplit.Noon -> return R.drawable.ic___clear_sky_morning// not in resources
                         else -> {
                             null
                         }
                     }
                 }

             }

             return null
         }

         fun getTimeSplit(time:Long):DaySplit?{

             val date = Date(System.currentTimeMillis())
             val calendar = GregorianCalendar()
             calendar.time = date

             when (calendar.get(Calendar.HOUR_OF_DAY)) {
                 in 0..6 -> return DaySplit.Dawn
                 in 7..11 -> return DaySplit.Morning
                 in 12..16 -> return DaySplit.Noon
                 in 17..19 -> return DaySplit.Evening
                 in 20..23 -> return DaySplit.Night
             }

             return null
         }
     }


}