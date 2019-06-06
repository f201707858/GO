package com.example.inseego

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.GridView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.inseego.adapters.Gridview_Adapter
import kotlinx.android.synthetic.main.activity_screens.*

class Screens : AppCompatActivity() {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                // message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //message  .setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //  message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screens)
        // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        var grid_view = findViewById<GridView>(R.id.grid_view)
        var gridview_Adapter = Gridview_Adapter(this, screen_list())
        grid_view?.setAdapter(gridview_Adapter)


        setSupportActionBar(toolbar)
        val actionBar = supportActionBar //In order to use various utility methods for action bar
        actionBar!!.title = "5G 360 VR"

        /*
        val tv = TextView(applicationContext)
        // Create a LayoutParams for TextView
        val lp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.CENTER_HORIZONTAL
        )
        // Apply the layout parameters to TextView widget
        tv.layoutParams = lp
        // tv.setTextColor(# ffffff)
        // Set TextView text alignment to center
        tv.gravity = Gravity.CENTER*/
        var tv = findViewById<TextView>(R.id.text1)
        var intent = getIntent()
        tv.setText(intent.getStringExtra("message"))


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.screens_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

fun screen_list(): ArrayList<String> {
    var screen_list = ArrayList<String>()
    for (i in 1..9) {
        screen_list.add("Camera" + i)
    }
    return screen_list
}
