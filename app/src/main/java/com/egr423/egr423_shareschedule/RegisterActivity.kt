package com.egr423.egr423_shareschedule

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import java.util.*

class RegisterActivity : AppCompatActivity() {


    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText

    private lateinit var date: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firstName = findViewById(R.id.firstNameField)
        lastName = findViewById(R.id.lastNameField)
        email = findViewById(R.id.emailField)
        password = findViewById(R.id.passwordField)
        date = findViewById(R.id.dateOfBirthField)

        setDatePicker(date)

    }

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
                this@RegisterActivity,
                datePickerOnDataSetListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    @SuppressLint("NewApi")
    private fun updateTextField(myCalendar: Calendar, dateEditText: EditText) {
        val myFormat: String = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dateEditText.setText(sdf.format(myCalendar.time))
    }
}
