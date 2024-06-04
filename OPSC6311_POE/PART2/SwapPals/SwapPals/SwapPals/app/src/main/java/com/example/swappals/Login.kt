package com.example.swappals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Login : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val editTextPassword = findViewById<EditText>(R.id.editTextTextPassword)

        val loginBtn = findViewById<Button>(R.id.btn_Login)
        loginBtn.setOnClickListener(View.OnClickListener
        {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty()){
                editTextEmail.error = "Field can't be empty, username or email required"

            }

            if (password.isEmpty()){
                editTextPassword.error = "Field can't be empty, password required"
            }

            startActivity(Intent( this, User_Dashboard::class.java)) })

    }
}