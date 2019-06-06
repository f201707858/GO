package com.example.inseego

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var click:Int = 0
    var down: Boolean = true
    var list: Array<String> = arrayOf("Device App", "User App")

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var Arrow = findViewById<ImageButton>(R.id.arrow)
        var Go = findViewById<Button>(R.id.go)
        var Sign = findViewById<Button>(R.id.sign_up)
        var List_Item = findViewById<ListView>(R.id.list_item)
        val list_adapter = ArrayAdapter(this, R.layout.list_item_view, list)
        Go.setOnClickListener({})
        List_Item.setAdapter(list_adapter)
        List_Item.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {
                if (position == 0) {
                   // if (view.id ==  R.id.go ) {
                        var intent1 = Intent(this@MainActivity,Login_Page::class.java)
                        startActivity(intent1)

                }

            }
        }
        Arrow.setOnClickListener({
            if (down) {
                List_Item.visibility = View.VISIBLE
                Arrow.setBackgroundResource(R.drawable.arrow_up)
                down = false
            } else {
                List_Item.visibility = View.INVISIBLE
                Arrow.setBackgroundResource(R.drawable.arrow_down)
                down = true
            }
        })


    }
}