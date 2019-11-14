package com.egr423.egr423_shareschedule

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.*


class DailyView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)


        //Calender
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        //Button click to show createEvent
        val event = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, mYear, mMonth, mDay ->
            //set to textView
            date.setText("" + mDay + "/" + mMonth + "/" + mYear)
        }, year, month, day)

        event.show()
    }

}