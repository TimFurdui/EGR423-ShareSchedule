package com.egr423.egr423_shareschedule

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.api.services.calendar.model.CalendarList
import java.util.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.Events
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class CalendarActivity : AppCompatActivity(){

    private lateinit var calendar : CalendarView
    private lateinit var name : TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        name = findViewById(R.id.nameOfUser)

        name.text = intent.extras!!["LoginActivity.nameOfUser"] as CharSequence?

//        calendar = findViewById(R.id.calendarView)
//        calendar.setOnDateChangeListener{view, year, month, dayOfMonth ->
//            val msg = "Selected date is" + dayOfMonth + "/" + (month + 1) + "/" + year
//            Toast.makeText(this@CalendarActivity, msg, Toast.LENGTH_SHORT).show()
//        }
//        val feed = FirebaseAuth.getInstance().currentUser.

    }

}