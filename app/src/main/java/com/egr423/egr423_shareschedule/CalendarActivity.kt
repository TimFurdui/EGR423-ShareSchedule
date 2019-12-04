package com.egr423.egr423_shareschedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.DateFormatSymbols


class CalendarActivity : AppCompatActivity() {


    //TODO DELETE AFTER TEST

    private lateinit var calendar: CalendarView
    private lateinit var userName: TextView
    private lateinit var createEventButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        userName = findViewById(R.id.nameOfUser)
        userName.text = CurrentUserSingleton.firstName + " " + CurrentUserSingleton.lastName
        calendar = findViewById(R.id.calendarView)
        createEventButton = findViewById(R.id.createEventButton)
        createCalendar()
        handleEventButtonClick()
        getEvents()
    }

    private fun createCalendar() {

        //TODO can make this change to open a new fragment upon click and to create event and populate in db
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->

            val dateSelected  = "${getMonthName(month)} $dayOfMonth, $year"

            val msg = "Selected date is $dateSelected"

            Toast.makeText(this@CalendarActivity, msg, Toast.LENGTH_SHORT).show()
        }

    }


    private fun handleEventButtonClick() {
        createEventButton.setOnClickListener {
            startActivity(Intent(this, AddEventActivity::class.java))
        }
    }

    private fun getMonthName(monthIndex: Int): String {
        return DateFormatSymbols().months[monthIndex].toString()
    }
}