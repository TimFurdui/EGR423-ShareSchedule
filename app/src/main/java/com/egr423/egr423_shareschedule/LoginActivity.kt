package com.egr423.egr423_shareschedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class LoginActivity : AppCompatActivity() {

    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEmail = findViewById(R.id.login_email)
        loginPassword = findViewById(R.id.login_pass)
        loginButton = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            loginUser()
        }

    }

    private fun loginUser() {
        val users = db.collection("users")
        users.document(loginEmail.text.toString()).get().addOnSuccessListener { document ->
            val dbPassword = document.getString("password")
            if (dbPassword == loginPassword.text.toString()) {
                users.document(loginEmail.text.toString()).get()
                    .addOnSuccessListener { documentSnapshot ->
                        val currentUser = documentSnapshot.toObject(User::class.java)!!
                        startActivity(
                            Intent(this, CalendarActivity::class.java).putExtra(
                                NAME_TAG,
                                currentUser.firstName + " " + currentUser.lastName
                            ).putExtra(EMAIL_TAG, currentUser.email)
                        )
                    }
                //TODO we can make user class singleton so we don't have to read from db everytime
            } else {
                Toast.makeText(
                    this,
                    "Invalid Email or Password!",
                    Toast.LENGTH_LONG
                )
                    .show()
            }

        }.addOnFailureListener { e ->
            Log.w(NAME_TAG, "Invalid Login Information : ", e)
        }

    }

    companion object {

        const val NAME_TAG = "LoginActivity.FullName"
        const val EMAIL_TAG = "LoginActivity.Email"
    }

}