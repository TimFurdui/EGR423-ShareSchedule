package com.egr423.egr423_shareschedule

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class CalendarActivity : AppCompatActivity(){

    private lateinit var calendar : CalendarView
    private lateinit var name : TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        name = findViewById(R.id.nameOfUser)

        name.text = intent.extras!!["LoginActivity.nameOfUser"] as CharSequence?

        calendar = findViewById(R.id.calendarView)
        calendar.setOnDateChangeListener{view, year, month, dayOfMonth ->
            val msg = "Selected date is" + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@CalendarActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }

}