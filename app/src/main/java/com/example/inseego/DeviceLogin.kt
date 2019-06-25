package com.example.inseego

import Model_login.User
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import android.text.TextUtils
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import com.example.inseego.Fragments.DeviceFragment


class Login_Page : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.device_login)

        var Email = findViewById<EditText>(R.id.email)
        var Password = findViewById<EditText>(R.id.password)
        var gotta = findViewById<Button>(R.id.btn_login)
        var user = User(Email.getText().toString(), Password.getText().toString())
        var d = Password.getText().toString()


        var mainLayout = findViewById<RelativeLayout>(R.id.mainLayout)

        var email_draw: Drawable = getResources().getDrawable(R.drawable.user_icon)
        email_draw.setBounds(0, 0, 20, 20)
        DrawableCompat.setTint(email_draw, Color.WHITE)
        Email.setCompoundDrawablesWithIntrinsicBounds(null, null, email_draw, null)

        var pass_draw: Drawable = getResources().getDrawable(R.drawable.eye_icon)
        pass_draw.setBounds(10, 10, 20, 20)
        pass_draw = DrawableCompat.wrap(pass_draw);
        DrawableCompat.setTint(pass_draw, Color.WHITE)
        Password.setCompoundDrawablesWithIntrinsicBounds(null, null, pass_draw, null)


        mainLayout.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(view: View, ev: MotionEvent): Boolean {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
                return false
            }
        })



        gotta.setShowSoftInputOnFocus(false)


        gotta.setOnClickListener({


            if (TextUtils.isEmpty(Email.text.toString())) {
                Toast.makeText(this, "User should not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(Password.text.toString())) {
                if (TextUtils.isEmpty(Password.text.toString())) {
                    Toast.makeText(this, "Password should not be empty", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    Toast.makeText(this, "Password length should be greater than 6", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val intent = Intent(this@Login_Page, add_group::class.java)
            intent.putExtra("message",  Email.getText().toString())
            startActivity(intent)


        })


    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@Login_Page, MainActivity::class.java))
    }
}

