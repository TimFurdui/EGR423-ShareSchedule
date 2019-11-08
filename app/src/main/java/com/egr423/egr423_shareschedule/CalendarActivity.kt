package com.egr423.egr423_shareschedule

import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
//import com.google.api.services.calendar.Calendar;
//import com.google.api.services.calendar.model.Event;




class CalendarActivity : AppCompatActivity(){



    private lateinit var calendar : CalendarView
    private lateinit var name : TextView

//    private val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        name = findViewById(R.id.nameOfUser)

//        name.text = currentUser!!.displayName
        name.text = "placeholde"
        calendar = findViewById(R.id.calendarView)
        calendar.setOnDateChangeListener{view, year, month, dayOfMonth ->
            val msg = "Selected date is" + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@CalendarActivity, msg, Toast.LENGTH_SHORT).show()
        }

    }

}