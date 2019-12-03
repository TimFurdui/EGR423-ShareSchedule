package com.egr423.egr423_shareschedule

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ViewEventsActivity : AppCompatActivity() {

    //DB
    private val db = FirebaseFirestore.getInstance()

    //Views
    private lateinit var eventDailyView: ListView

    //Adapter
    private lateinit var eventAdapter: EventsAdapter
    private lateinit var eventArrayList: Array<Event>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventDailyView = findViewById(R.id.list_view)

    }

    private fun getEvents() {

        db.collection("users").document(CurrentUserSingleton.userEmail).collection("calendarEvents")


    }

}