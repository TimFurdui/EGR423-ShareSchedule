package com.egr423.egr423_shareschedule

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddEvent : AppCompatActivity() {
    private lateinit var eventTitle: EditText
    private lateinit var eventTime: EditText
    //    private lateinit var attendees : List<String>
    private lateinit var comments: EditText

    var eventCreatorEmail: String = ""


    //TODO USE THIS ACTIVITY TO CREATE AN EVENT
    //TODO WILL FIGURE OUT ATTENDEES LATER
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventTitle = findViewById(R.id.eventName)
        eventTime = findViewById(R.id.eventTime)
        comments = findViewById(R.id.eventComments)
    }


}