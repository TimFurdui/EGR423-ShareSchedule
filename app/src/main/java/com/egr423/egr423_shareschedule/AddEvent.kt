package com.egr423.egr423_shareschedule

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddEvent : AppCompatActivity() {
    private lateinit var eventTitle: EditText
    private lateinit var eventTime: EditText
    //    private lateinit var attendees : List<String>
    private lateinit var comments: EditText

    private lateinit var eventCreatorEmail: String



    //TODO USE THIS ACTIVITY TO CREATE AN EVENT


    //FOR ATTENDEES CAN ALERT THE USER AND IF THEY ACCEPT THEY WILL SHOW UP ON THE ATTENDEES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addevent)

        eventTitle = findViewById(R.id.eventName)
        eventTime = findViewById(R.id.eventTime)
        comments = findViewById(R.id.eventComments)
        eventCreatorEmail = intent.getStringExtra(CalendarActivity.EMAIL_TAG)
        println(eventCreatorEmail)
    }


}