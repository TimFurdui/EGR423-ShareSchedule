package com.egr423.egr423_shareschedule

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.doAsync
import java.util.*
import kotlin.collections.ArrayList

class RecyclerFriendsAdapter(
    contextParameterVal: Context,
    friendsParameterValue: ArrayList<String>?
) : RecyclerView.Adapter<RecyclerFriendsAdapter.ViewHolder>() {

    var context: Context = contextParameterVal
    var friendEmailsList: ArrayList<String>? = friendsParameterValue
    //    var friendNameList: ArrayList<String>? = null
    private lateinit var selectedDate: String
    val db = FirebaseFirestore.getInstance()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.friend_list_item, parent, false)
        var holder: ViewHolder = ViewHolder(view)
        return holder
    }


    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.w(TAG, "onBindViewHolder: called.")
//        holder.friendName.setText(friendNameList?.get(position))
        holder.friendEmail.setText(friendEmailsList?.get(position))

        holder.parentLayout.setOnClickListener {

            setDatePicker()
            val selectedFriendEmail = friendEmailsList?.get(position).toString()

//            val date = java.text.SimpleDateFormat(
//                "dd-MM-yyyy",
//                Locale.US
//            ).parse(selectedDate)

//            var eventsDocumentList =
//                db.collection("users").document(CurrentUserSingleton.userEmail)
//                    .collection("calendarEvents")
//
//            var eventsFriendDocumentList =
//                db.collection("users").document(selectedFriendEmail)
//                    .collection("calendarEvents")

//            eventsDocumentList.get()
//                .addOnSuccessListener {
//                    //TODO QUERY DB AND SEE IF IT TIMES WORK FOR BOTH
//                    //set selected time to a date object and query db for that date object
//                    var listOfEvents = ArrayList<Event>()
//
//                    for (document in it.documents) {
//                        var timeStamp: Timestamp = document["eventTime"] as Timestamp
//                        var timeStampDate = timeStamp.toDate().toString().substring(0, 10)
//                        if (timeStampDate.equals(date.toString()/*.substring(0, 10)*/))
//                            listOfEvents.add(document.toObject(Event::class.java)!!)
//                    }
//
//                    //TODO IF TIME WORKS FOR BOTH THEN ADD TO BOTH
//                    eventsFriendDocumentList.get()
//                        .addOnSuccessListener {
//                            for (document in it.documents) {
//                                var friendTimeStamp: Timestamp = document["eventTime"] as Timestamp
//                                var friendTimeStampDate =
//                                    friendTimeStamp.toDate().toString().substring(0, 10)
//                                if (friendTimeStampDate.equals(date.toString()/*.substring(0, 10)*/))
//                                    listOfEvents.add(document.toObject(Event::class.java)!!)
//                            }
//                        }
//                    if (listOfEvents.size == 2 || listOfEvents.size == 1) {
//                        Toast.makeText(
//                            context,
//                            "Schedule conflict, unable to add event!",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
        }
    }


    override fun getItemCount(): Int {
        return friendEmailsList!!.size
    }


    inner class ViewHolder : RecyclerView.ViewHolder {

        //        var friendName: TextView
        var friendEmail: TextView
        var parentLayout: LinearLayout


        constructor(itemView: View) :
                super(itemView) {
//            friendName = itemView.findViewById(R.id.friendItemName)
            friendEmail = itemView.findViewById(R.id.friendItemEmail)
            parentLayout = itemView.findViewById(R.id.friendParent_listItem_layout)
        }
    }


    companion object {
        private val TAG = "RecyclerFriendsAdapter"
    }

    //Used to create popup Calendar
    @SuppressLint("NewApi")
    private fun setDatePicker() {
        val currentDate = Calendar.getInstance()
        var date = Calendar.getInstance()

        DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                date.set(year, monthOfYear, dayOfMonth)
                TimePickerDialog(
                    context,
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        date.set(Calendar.MINUTE, minute)
                        Log.v(TAG, "The choosen one " + date.getTime())
                        updateTextField(date)
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
    private fun updateTextField(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy HH:mm"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        selectedDate = sdf.format(myCalendar.time)
        Log.w(TAG, selectedDate)
    }


//    fun queryDbForName() {
//
//
//            Log.w("FRIEND EMAIL LIST", friendEmailsList.toString())
//            var friendName: ArrayList<String>? = null
//
//            for (string in friendEmailsList!!) {
//                db.collection("users").document(string).get().addOnSuccessListener { document ->
//
//                    val firstName = document.get("firstName") as String?
//                    val lastName = document.get("lastName") as String?
//                    val name = "$firstName $lastName"
//
//                    friendName?.add(name)
//
//                    Log.w(TAG, document.toString())
//                    Log.w("NAME TEST", name)
//                    Log.w("LIST TEST ", friendName.toString())
//                }
//            }
//
//    }

}