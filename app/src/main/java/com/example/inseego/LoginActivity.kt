package com.example.inseego

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var click: Int = 0
    var down: Boolean = true
    var list: Array<String> = arrayOf("Device App", "User App")

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        //var Arrow = findViewById<ImageButton>(R.id.arrow)
        var Go = findViewById<Button>(R.id.go)
        // var Sign = findViewById<Button>(R.id.sign_up)
        var List_Item = findViewById<ListView>(R.id.list_item)
        val list_adapter = ArrayAdapter(this, R.layout.login_list_item, list)
        val edit_text = findViewById<EditText>(R.id.slider)

        List_Item.setAdapter(list_adapter)
        //function can also be given as an expression
        List_Item.onItemClickListener =
            object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>, view: View,
                    position: Int, id: Long
                ) {
                    if (position == 0  ) {
                        List_Item.visibility = View.INVISIBLE
                        edit_text.setText(list[position])
                        edit_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0)
                        down=true
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
                List_Item.visibility = View.VISIBLE
                edit_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0)
                down = false
            } else {
                List_Item.visibility = View.INVISIBLE
                edit_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0)
                down = true
            }


        })
        edit_text.setShowSoftInputOnFocus(false)



    }
}


/*     Arrow.setOnClickListener(
     {
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
}*/