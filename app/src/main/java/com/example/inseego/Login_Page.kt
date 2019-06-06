package com.example.inseego

import Model_login.User
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_login__page.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView


class Login_Page : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login__page)

        var Email = findViewById<EditText>(R.id.email)
        var Password = findViewById<EditText>(R.id.password)
        var gotta = findViewById<Button>(R.id.btn_login)
        var user = User(Email.getText().toString(), Password.getText().toString())

        //   var mainLayout = findViewById<RelativeLayout>(R.id.mainLayout)
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(Password.getWindowToken(), 0)
        gotta.setOnClickListener({
            if (user.isValidData()) {
                Toast.makeText(this, "LOGIN_SUCCESS", Toast.LENGTH_SHORT).show()

            } else {
                print(user.Password.length)
                Toast.makeText(this, "LOGIN_FAILED", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Login_Page, Screens::class.java)
                intent.putExtra("message", Email.getText().toString())
                startActivity(intent)
            }
        })
    }
}


