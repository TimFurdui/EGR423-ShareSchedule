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
    }

    private fun createCalendar() {

        //TODO can make this change to open a new fragment upon click and to create event and populate in db
        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->

            val dateSelected = "$dayOfMonth-${month + 1}-$year"

            startActivity(
                Intent(this, ViewEventsActivity::class.java).putExtra(
                    DATETAG,
                    dateSelected
                )
            )
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

    companion object {
        val TAG = "CalendarActivity"
        val DATETAG = "CalendarActivityDate"
    }
}