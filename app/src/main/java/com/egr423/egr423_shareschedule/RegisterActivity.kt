package com.egr423.egr423_shareschedule

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

    private val db = FirebaseFirestore.getInstance()

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

            if (firstName.text.isNotEmpty()
                && lastName.text.isNotEmpty()
                && email.text.isNotEmpty()
                && password.text.isNotEmpty()
                && date.text.isNotEmpty()
            ) {
                addUserToDb()
            } else {
                Toast.makeText(
                    this,
                    "Please make sure none of the above fields are empty!",
                    Toast.LENGTH_SHORT
                ).show()
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
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dateEditText.setText(sdf.format(myCalendar.time))
    }

    @SuppressLint("NewApi")
    private fun addUserToDb() {

        val userExistQuery = db.collection("users")

        userExistQuery.document(email.text.toString().toLowerCase()).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {

                    Toast.makeText(
                        this,
                        "Account already registered with supplied email, choose another.",
                        Toast.LENGTH_LONG
                    ).show()

                    Log.w(
                        TAG,
                        "Account already exists with supplied email : ${email.text}"
                    )

                } else {
                    val user = hashMapOf(
                        "firstName" to firstName.text.toString(),
                        "lastName" to lastName.text.toString(),
                        "email" to email.text.toString().toLowerCase(),
                        "password" to password.text.toString(),
                        "birthDate" to SimpleDateFormat(
                            "dd-MM-yyyy",
                            Locale.US
                        ).parse(date.text.toString()),
                        "userComments" to listOf<String>(),
                        "userFriends" to listOf<String>()
                    )

                    //Create a new document for the User with the ID as Email of user
                    //useful to query db and check if user already exists
                    try {
                        db.collection("users").document(email.text.toString().toLowerCase()).set(user)
                            .addOnSuccessListener {
                                Log.d(
                                    TAG,
                                    "DocumentSnapshot added with ID as Email: $email"
                                )
                                startActivity(Intent(this, LoginActivity::class.java))
                                Toast.makeText(
                                    this,
                                    "Account Created",
                                    Toast.LENGTH_LONG
                                ).show()
                            }.addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
    }


}
