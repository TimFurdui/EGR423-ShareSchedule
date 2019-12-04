package com.egr423.egr423_shareschedule

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.DialogTitle
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

    //TODO should be enough to get started on adapter
    private fun getEvents() {

        var listOfEvents = ArrayList<Event>()

        var events = db.collection("users").document(CurrentUserSingleton.userEmail)
            .collection("calendarEvents")

        events.whereEqualTo("time", "November 19, 2019").get().addOnSuccessListener { documents ->

            for (document in documents) {
                listOfEvents.add(document.toObject(Event::class.java))
            }

        }.addOnFailureListener {
            Log.w(TAG, "Invalid TIME")
        }
    }

    companion object {

        const val TAG = "ViewEventsActivity"
    }


}