package com.egr423.egr423_shareschedule

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception
import android.text.format.DateFormat
import java.util.*

class AddEventActivity : AppCompatActivity() {

    private val TAG = "AddEvent"

    private lateinit var eventTitle: EditText
    private lateinit var eventTimeInput: EditText
    //    private lateinit var attendees : List<String>
    private lateinit var comments: EditText
    private lateinit var eventCreatorEmail: String
    private lateinit var submitButton: Button
    private val db = FirebaseFirestore.getInstance()

    //TODO USE THIS ACTIVITY TO CREATE AN EVENT
    //TODO FOR ATTENDEES ALERT THE USER AND IF THEY ACCEPT THEY WILL SHOW UP ON THE ATTENDEES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addevent)

        eventTitle = findViewById(R.id.eventName)
        eventTimeInput = findViewById(R.id.eventTime)
        comments = findViewById(R.id.eventComments)
        submitButton = findViewById(R.id.submit_button)
        eventCreatorEmail = intent.getStringExtra(CalendarActivity.EMAIL_TAG)

        eventTimeInput.setOnClickListener {
//            TimePickerFragment().show(supportFragmentManager, "timePicker")
            setDatePicker(eventTimeInput)
        }
        submitButton.setOnClickListener { addEventToDb() }

    }


    private fun addEventToDb() {
        //Check if event in database if it is tell user choose diff name If not then create event
        val eventList =
            db.collection("users").document(eventCreatorEmail).collection("calendarEvents")
        eventList.document(eventTitle.text.toString()).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    Toast.makeText(
                        this,
                        "Event already exists with this name! Choose another.",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.w(TAG, "Event already exists with event name : ${eventTitle.text}")
                } else {
                    val currentEvent = hashMapOf(
                        "eventTitle" to eventTitle.text.toString(),
                        //TODO for event time need to make it input hour/minute as well
                        "eventTime" to eventTimeInput.text.toString()/*SimpleDateFormat(
                            "dd-MM-yyyy",
                            Locale.US
                        ).parse(eventTime.text.toString())*/,
                        "eventCreatorEmail" to eventCreatorEmail,
                        "comments" to comments.text.toString(),
                        //TODO populate this with emails, (Create function that sends alert to user if accepted add them to attendees)
                        "attendees" to "test@gmail.com"
                    )
                    //THIS IS THE PART THAT ADDS TO THE DB
                    try {
                        eventList.document(eventTitle.text.toString()).set(currentEvent)
                            .addOnSuccessListener {
                                Log.d(TAG, "Document added with uniqueID as: ${eventTitle.text}")
                            }.addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
    }


//    class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
//
//        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//            val c = Calendar.getInstance()
//            val hour = c.get(Calendar.HOUR_OF_DAY)
//            val minute = c.get(Calendar.MINUTE)
//
//            return TimePickerDialog(
//                activity,
//                this,
//                hour,
//                minute,
//                DateFormat.is24HourFormat(activity)
//            )
//        }
//
//        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//            //Do something with time chosen by user
//        }
//    }
//Used to create popup Calendar
@SuppressLint("NewApi")
private fun setDatePicker(dateEditText: EditText) {
    val myCalendar = Calendar.getInstance()

    val datePickerOnDataSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateTextField(myCalendar, dateEditText)
        }

    dateEditText.setOnClickListener {
        DatePickerDialog(
            this@AddEventActivity,
            datePickerOnDataSetListener,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

}

    @SuppressLint("NewApi")
    private fun updateTextField(myCalendar: Calendar, dateEditText: EditText) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dateEditText.setText(sdf.format(myCalendar.time))
    }

}