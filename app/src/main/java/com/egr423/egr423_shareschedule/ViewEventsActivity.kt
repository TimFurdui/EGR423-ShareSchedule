package com.egr423.egr423_shareschedule

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ViewEventsActivity : AppCompatActivity() {


    //DB
    private val db = FirebaseFirestore.getInstance()

    //Views
    private lateinit var eventView: RecyclerView

    //Adapter
    private lateinit var eventAdapter: RecyclerEventsAdapter

    //    private lateinit var eventArrayList: Array<Event>
    private lateinit var currentDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewevents)
        eventView = findViewById(R.id.eventRecyclerView)
        currentDate = findViewById(R.id.currentDate)
        getEvents()
    }

    private fun getEvents() {

        val dbDateQuery = SimpleDateFormat(
            "dd-MM-yyyy hh:mm",
            Locale.US
        ).parse(intent.getStringExtra(CalendarActivity.DATETAG))

        val date = SimpleDateFormat(
            "dd-MM-yyyy",
            Locale.US
        ).parse(intent.getStringExtra(CalendarActivity.DATETAG))

        var eventsDocumentList = db.collection("users").document(CurrentUserSingleton.userEmail)
            .collection("calendarEvents")

        //TODO IN ORDER TO FIX THE TIME I CHANGED THE EVENTTIME TO AN ARRAY SO WE CAN USE THE ARRAYCONTAINS AND QUERY FOR THE DATE
        eventsDocumentList.whereEqualTo("eventTime", date)
            .get()
            .addOnSuccessListener { eventDocuments ->
                var listOfEvents = ArrayList<Event>()
                for ((i, event) in eventDocuments.withIndex()) {
                    listOfEvents.add(event.toObject(Event::class.java))
                    Log.w(TAG, listOfEvents[i].toString())
                }
                var recyclerView: RecyclerView = findViewById(R.id.eventRecyclerView)
                eventAdapter = RecyclerEventsAdapter(this, listOfEvents)
                recyclerView.adapter = eventAdapter
                recyclerView.layoutManager = LinearLayoutManager(this)

                currentDate.setText(date.toString())

            }.addOnFailureListener {
                Log.w(TAG, "Invalid TIME")
            }
    }

//    private fun getMonthName(monthIndex: Int): String {
//        return DateFormatSymbols().months[monthIndex].toString()
//    }

    companion object {

        const val TAG = "ViewEventsActivity"
    }


}