package com.egr423.egr423_shareschedule

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class CalendarActivity : AppCompatActivity() {


    private lateinit var calendar: CalendarView
    private lateinit var name: TextView
    private lateinit var createEventButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        name = findViewById(R.id.nameOfUser)
        populateUserData()
        calendar = findViewById(R.id.calendarView)
        createEventButton = findViewById(R.id.createEventButton)
        createCalendar()
    }


    private fun populateUserData() {
        name.text = intent.getStringExtra(LoginActivity.NAME_TAG)

    }

    private fun createCalendar() {

        //TODO can make this change to open a new fragment upon click and to create event and populate in db
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val msg = "Selected date is" + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@CalendarActivity, msg, Toast.LENGTH_SHORT).show()
        }

    }

//    private fun

}