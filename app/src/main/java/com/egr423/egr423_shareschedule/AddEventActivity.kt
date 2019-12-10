package com.egr423.egr423_shareschedule

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception
import java.util.*


class AddEventActivity : AppCompatActivity() {

    private val TAG = "AddEventActivity"

    private lateinit var eventTitle: EditText
    private lateinit var eventTimeInput: EditText
    //    private lateinit var attendees: List<String>
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
        eventCreatorEmail = CurrentUserSingleton.userEmail

        eventTimeInput.setOnClickListener {
            setDatePicker(eventTimeInput)
        }
        submitButton.setOnClickListener {
            if (eventTitle.text.isNotEmpty() && eventTimeInput.text.isNotEmpty()) {

                addEventToDb()
                Toast.makeText(
                    this,
                    "Event ${eventTitle.text} was added!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Please make sure none of the above fields are empty!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }


    @SuppressLint("NewApi")
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

                    //TODO MIGHT NEED TO CHANGE THE TYPE OF DATA BEING USED
                    val currentEvent = hashMapOf(
                        "eventTitle" to eventTitle.text.toString(),
                        //TODO for event time need to make it input hour/minute as well
                        "eventTime" to
                                SimpleDateFormat(
                                    "dd-MM-yyyy hh:mm",
                                    Locale.US
                                ).parse(eventTimeInput.text.toString()),
                        "eventDaysArray" to arrayListOf<Date>(
                            SimpleDateFormat(
                                "dd-MM-yyyy hh:mm",
                                Locale.US
                            ).parse(eventTimeInput.text.toString())
                        ),
                        "eventCreatorEmail" to eventCreatorEmail,
                        "comments" to comments.text.toString(),
                        //TODO populate this with emails, (Create function that sends alert to user if accepted add them to attendees)
                        "attendees" to listOf<String>()
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


    //Used to create popup Calendar
    @SuppressLint("NewApi")
    private fun setDatePicker(dateEditText: EditText) {
        val currentDate = Calendar.getInstance()
        var date = Calendar.getInstance()

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                date.set(year, monthOfYear, dayOfMonth)
                updateTextField(date, eventTimeInput)
                TimePickerDialog(
                    this,
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        date.set(Calendar.MINUTE, minute)
                        Log.v(TAG, "The choosen one " + date.getTime())
                        updateTextField(date, eventTimeInput)
                    },
                    currentDate.get(Calendar.HOUR_OF_DAY),
                    currentDate.get(Calendar.MINUTE),
                    false
                ).show()
            },
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DATE)

        ).show()
    }

    @SuppressLint("NewApi")
    private fun updateTextField(myCalendar: Calendar, dateEditText: EditText) {
        val myFormat = "dd-MM-yyyy HH:mm"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dateEditText.setText(sdf.format(myCalendar.time))
    }

}