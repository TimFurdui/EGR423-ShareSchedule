package com.egr423.egr423_shareschedule

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
            if (loginEmail.text.isNotEmpty() && loginPassword.text.isNotEmpty()) {
                loginUser()
            } else {
                Toast.makeText(this, "Please enter a password & username", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun loginUser() {
        val users = db.collection("users")
        users.document(loginEmail.text.toString().toLowerCase()).get()
            .addOnSuccessListener { document ->
                val dbPassword = document.getString("password")
                if (dbPassword == loginPassword.text.toString()) {
                    users.document(loginEmail.text.toString().toLowerCase()).get()
                        .addOnSuccessListener { documentSnapshot ->
                            val currentUser = documentSnapshot.toObject(User::class.java)!!

                            //Store user data in Singleton class to be easily accessed across application
                            CurrentUserSingleton.userEmail =
                                currentUser.email.toString().toLowerCase()
                            CurrentUserSingleton.firstName = currentUser.firstName.toString()
                            CurrentUserSingleton.lastName = currentUser.lastName.toString()

                            startActivity(
                                Intent(this, CalendarActivity::class.java)
                            )
                        }
                } else {

                    val invalidLoginDialog = AlertDialog.Builder(this)

                    invalidLoginDialog.setTitle("Invalid Login Information!")

                    invalidLoginDialog.setCancelable(true)
                    invalidLoginDialog.setPositiveButton(
                        "Register new account",
                        DialogInterface.OnClickListener { dialog, id ->
                            startActivity(Intent(this, RegisterActivity::class.java))
                            finish()
                        })
                    invalidLoginDialog.setNegativeButton(
                        "Cancel",
                        DialogInterface.OnClickListener { dialog, which ->
                            dialog.cancel()
                        })
                    invalidLoginDialog.show()
                }

            }.addOnFailureListener { e ->
            Log.w(NAME_TAG, "Invalid Login Information : ", e)
        }

    }

    companion object {

        const val NAME_TAG = "LoginActivity.FullName"
    }

}
