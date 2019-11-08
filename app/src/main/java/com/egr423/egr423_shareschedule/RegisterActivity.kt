package com.egr423.egr423_shareschedule

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private val TAG = "RegisterActivity"

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText

    private lateinit var date: EditText

    private lateinit var submitButton: Button

    val db = FirebaseFirestore.getInstance()

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Inflate the elements on page
        firstName = findViewById(R.id.firstNameField)
        lastName = findViewById(R.id.lastNameField)
        email = findViewById(R.id.emailField)
        password = findViewById(R.id.passwordField)
        date = findViewById(R.id.dateOfBirthField)


        setDatePicker(date)

        submitButton = findViewById(R.id.register_submit_button)

        //Set on click listener to call write to DB function
        submitButton.setOnClickListener {

            //TODO check to make sure there are no duplicates (USE EMAIL)
            if (checkIfUserExists(email.text.toString())){

            } else {

            //Reads the set text from the user input
                writeUserToDb(
                    firstName.text.toString(),
                    lastName.text.toString(),
                    email.text.toString(),
                    password.text.toString(),
                    SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(date.text.toString())
                )
            }

        }

    }

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

    private fun writeUserToDb(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        birthDate: Date
    ) {
        //User Privilege is initially set to 0
        val user = hashMapOf(
            "userID" to UUID.randomUUID().toString(),
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "password" to password,
            "birthDate" to birthDate,
            "userPrivilege" to 0,
            "userComments" to listOf("")
        )
        try {
            db.collection("users").add(user).addOnSuccessListener { documentReference ->
                Log.d(
                    TAG,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )
            }.addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun checkIfUserExists(email: String): Boolean {

        //can also just do a query on all documents & check if it contains email
        val usersRef = db.collection("users")
        val result = usersRef.whereEqualTo("email", email)
        return if (result.toString() == email) {

            Log.w(TAG, "FOUND EMAIL $result")
            true
        } else
            false

        //TODO can create a toast to alert user if account is already registered with this email
    }


}
