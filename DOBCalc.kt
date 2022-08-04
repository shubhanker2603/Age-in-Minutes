package com.example.dobcalc

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvdate: TextView? = null
    private var tvageinmin:TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btndatapicker : Button = findViewById(R.id.btndatepicker)
        tvdate = findViewById(R.id.tvSelectedDate)
        tvageinmin = findViewById(R.id.tvageinmin)
        btndatapicker.setOnClickListener {
            clickDatePicker()

        }
    }
    private fun clickDatePicker(){

        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)
        val day = mycalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
           DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
               Toast.makeText(this,
                   "Year was $year,month was ${month+1}, day of month $dayOfMonth",Toast.LENGTH_LONG).show()

               val selecteddate = "$dayOfMonth/${month+1}/$year"
               tvdate?.setText(selecteddate)

               val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
               val thedate = sdf.parse(selecteddate)
               thedate.let {
                   val dateinminute = thedate.time/60000

                   val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                   currentDate?.let{
                       val currentdateinminutes = currentDate.time/60000

                       val diff = currentdateinminutes - dateinminute

                       tvageinmin?.text = diff.toString()
                   }

               }

           },
            year,
            month,
            day
            )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()



    }
}
