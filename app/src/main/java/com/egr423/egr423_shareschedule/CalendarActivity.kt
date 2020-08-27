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


    private lateinit var calendar: CalendarView
    private lateinit var userName: TextView
    private lateinit var createEventButton: Button
    private lateinit var viewFriendButton: Button

//    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        userName = findViewById(R.id.nameOfUser)
        userName.text = CurrentUserSingleton.firstName + " " + CurrentUserSingleton.lastName
        calendar = findViewById(R.id.calendarView)
        createEventButton = findViewById(R.id.createEventButton)
        viewFriendButton = findViewById(R.id.viewFriendsButton)
        createCalendar()
        handleEventButtonClick()
        handleFriendButtonClick()
    }

    private fun createCalendar() {

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

    private fun handleFriendButtonClick() {
        viewFriendButton.setOnClickListener {
            startActivity(Intent(this, ViewFriendsActivity::class.java))
        }
    }

    companion object {
        val TAG = "CalendarActivity"
        val DATETAG = "CalendarActivityDate"
    }
}