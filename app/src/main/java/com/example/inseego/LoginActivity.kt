package com.example.inseego

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.*
import android.widget.RelativeLayout
import android.view.ViewTreeObserver


class MainActivity : AppCompatActivity() {
    var click: Int = 0
    var down: Boolean = true
    var list: Array<String> = arrayOf("Device App", "User App")

    //  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        var Go = findViewById<Button>(R.id.go)
        var List = findViewById<ListView>(R.id.list_item)
        val list_adapter = ArrayAdapter(this, R.layout.login_list_item, list)
        val edit_text = findViewById<EditText>(R.id.slider)

        /* val relativeLayoutSameDay = findViewById<RelativeLayout>(R.layout.login_activity)
          val viewTreeObserver = relativeLayoutSameDay.getViewTreeObserver()
          viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
              override fun onGlobalLayout() {
                  relativeLayoutSameDay.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                  val height = relativeLayoutSameDay.getMeasuredHeight()
                  val head_params = relativeLayoutSameDay.getLayoutParams() as RelativeLayout.LayoutParams
                  var ARG_ASPECT_RATIO = height * 200
                  head_params.setMargins(0, 0, 0, (height * ARG_ASPECT_RATIO) as Int)
                  relativeLayoutSameDay .setLayoutParams(head_params)
              }
          })*/

        List.setAdapter(list_adapter)
        //function can also be given as an expression
        List.onItemClickListener =
            object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>, view: View,
                    position: Int, id: Long
                ) {
                    if (position == 0) {
                        List.visibility = View.INVISIBLE
                        edit_text.setText(list[position])
                        edit_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0)
                        down = true
                        Go.setOnClickListener({
                            var intent1 = Intent(this@MainActivity, Login_Page::class.java)
                            startActivity(intent1)


                        }
                        )
                    }
                }
            }


        edit_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0)
        edit_text.setOnClickListener({
            if (down) {
                List.visibility = View.VISIBLE
                edit_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0)
                down = false
            } else {
                List.visibility = View.INVISIBLE
                edit_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0)
                down = true
            }


        })
        edit_text.setShowSoftInputOnFocus(false)


    }
}

