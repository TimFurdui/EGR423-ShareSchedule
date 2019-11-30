package com.egr423.egr423_shareschedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class MainActivity : AppCompatActivity() {


    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.register_button)


        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }


    }

}
