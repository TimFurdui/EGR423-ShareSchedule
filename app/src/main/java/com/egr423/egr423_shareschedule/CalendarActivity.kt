package com.egr423.egr423_shareschedule

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class CalendarActivity : AppCompatActivity() {


    private lateinit var calendar: CalendarView
    private lateinit var userName: TextView
    private lateinit var createEventButton: Button
    private lateinit var userEmail: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        userName = findViewById(R.id.nameOfUser)
        populateUserData()
        calendar = findViewById(R.id.calendarView)
        createEventButton = findViewById(R.id.createEventButton)
        createCalendar()
        handleEventButtonClick()
    }


    private fun populateUserData() {
        userName.text = intent.getStringExtra(LoginActivity.NAME_TAG)
        userEmail = intent.getStringExtra(LoginActivity.EMAIL_TAG)
    }

    private fun createCalendar() {

        //TODO can make this change to open a new fragment upon click and to create event and populate in db
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val msg = "Selected date is" + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@CalendarActivity, msg, Toast.LENGTH_SHORT).show()
        }

    }

    private fun handleEventButtonClick() {
        createEventButton.setOnClickListener {
            startActivity(Intent(this, AddEvent::class.java).putExtra(EMAIL_TAG, userEmail))
        }
    }

    companion object {
        const val EMAIL_TAG = "CalendarActivity.Email"
    }
}