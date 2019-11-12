package com.egr423.egr423_shareschedule

//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity()/*, View.OnClickListener */ {

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

        users.document(loginEmail.text.toString()).get().addOnSuccessListener {document ->
            val dbPassword = document.getString("password")
            if (dbPassword == loginPassword.text.toString()){

                //TODO we can make user class singleton so we don't have to read from db everytime

                startActivity(Intent(this, CalendarActivity::class.java))
            }


        }.addOnFailureListener { e ->

            Toast.makeText(this, "Account information provided doesn't exist!", Toast.LENGTH_LONG)
                .show()
            Log.w(TAG, "Invalid Login Information : ", e)
        }

    }

}
