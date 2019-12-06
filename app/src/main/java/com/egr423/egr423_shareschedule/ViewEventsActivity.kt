package com.egr423.egr423_shareschedule

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ViewEventsActivity : AppCompatActivity() {


    //DB
    private val db = FirebaseFirestore.getInstance()

    //Views
    private lateinit var eventDailyView: RecyclerView

    //Adapter
    private lateinit var eventAdapter: RecyclerEventsAdapter
    private lateinit var eventArrayList: Array<Event>
    private lateinit var currentDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewevents)
        eventDailyView = findViewById(R.id.recycler_view)
        currentDate = findViewById(R.id.currentDate)
        getEvents()
    }

    //TODO send in date clicked from calendarActivity as an Extra
    private fun getEvents() {

        val date = SimpleDateFormat(
            "dd-MM-yyyy",
            Locale.US
        ).parse(intent.getStringExtra(CalendarActivity.DATETAG))

        Log.w(TAG, "BEFORE QUERY")

        var eventsDocumentList = db.collection("users").document(CurrentUserSingleton.userEmail)
            .collection("calendarEvents")

        eventsDocumentList.whereEqualTo("eventTime", date)
            .get()
            .addOnSuccessListener { eventDocuments ->
                var listOfEvents = ArrayList<Event>()
                for (event in eventDocuments) {
                    listOfEvents.add(event.toObject(Event::class.java))
                }


                var recyclerView: RecyclerView = findViewById(R.id.recycler_view)
                eventAdapter = RecyclerEventsAdapter(this, listOfEvents)
                recyclerView.adapter = eventAdapter
                recyclerView.layoutManager = LinearLayoutManager(this)
                currentDate.setText(date.toString())
                //TODO deal with logic in here

            }.addOnFailureListener {
                Log.w(TAG, "Invalid TIME")
            }
    }

    companion object {

        const val TAG = "ViewEventsActivity"
    }


}