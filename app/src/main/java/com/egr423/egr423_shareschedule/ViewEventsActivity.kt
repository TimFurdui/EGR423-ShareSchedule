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
        setContentView(R.layout.activity_viewevents)
        eventDailyView = findViewById(R.id.list_view)

        getEvents()

    }

    //TODO should be enough to get started on adapter
    private fun getEvents() {

        var listOfEvents = ArrayList<Event>()

        var events = db.collection("users").document(CurrentUserSingleton.userEmail)
            .collection("calendarEvents")

        events.whereGreaterThanOrEqualTo("eventTime", "24-11-2019").get().addOnSuccessListener { documents ->

            for (document in documents) {
                listOfEvents.add(document.toObject(Event::class.java))
                Log.w(TAG, listOfEvents[0].eventCreatorEmail)
                Log.w(TAG, listOfEvents[0].eventTitle)
            }
        }.addOnFailureListener {
            Log.w(TAG, "Invalid TIME")
        }
    }

    companion object {

        const val TAG = "ViewEventsActivity"
    }


}