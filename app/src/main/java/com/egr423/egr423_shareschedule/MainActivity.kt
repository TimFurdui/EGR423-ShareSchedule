package com.egr423.egr423_shareschedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.register_button)

        registerButton.setOnClickListener {
            //TODO GO TO THE REGISTER FRAGMENT
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            //TODO GO TO THE LOGIN ACTIVITY
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }
}
