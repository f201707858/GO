package com.example.inseego

import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import com.example.inseego.adapters.Gridview_Adapter
import com.loopj.android.http.JsonHttpResponseHandler
import omdb.Omdb_Values
import omdb.omdbclient
import org.json.JSONObject
import cz.msebera.android.httpclient.Header
import android.widget.AdapterView.OnItemLongClickListener

import android.support.annotation.NonNull
import android.view.MenuItem
import java.nio.file.Files.size




class Screens : AppCompatActivity() {


    /*  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
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
      }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monitoring) // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override
            fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.navigation_home ->
                        // do something here
                        return true
                    R.id.navigation_dashboard ->
                        // do something here
                        return true
                    R.id.navigation_notifications ->
                        // do something here
                        return true
                    else -> return true
                }
            }
        })
      /*  for (i in 0 until bottomNavigationView.getMenu().size()) {
            bottomNavigationView.getMenu().getItem(i).setChecked(false)
        }*/



        var tv = findViewById<TextView>(R.id.text1)
        var intent = getIntent()
        tv.setText(intent.getStringExtra("message"))

        var grid_view = findViewById<GridView>(R.id.grid_view)
        grid_view?.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override
            fun onItemClick(parent: AdapterView<*>, v: View, position: Int, id: Long) {
                Toast.makeText(this@Screens, "Click Listener", Toast.LENGTH_SHORT).show()
                // open the pager activity
            }
        })
        grid_view.setOnItemLongClickListener(object : OnItemLongClickListener {
            override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long):Boolean {
                bottomNavigationView.visibility=View.VISIBLE
                return true
            }
        })


        var screen_list: ArrayList<Omdb_Values> = web_service()
        var gridview_Adapter = Gridview_Adapter(this, screen_list)
        grid_view?.setAdapter(gridview_Adapter)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.screens_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    fun web_service(): ArrayList<Omdb_Values> {
        var movies = arrayOf("Stree", "Andhadhun", "3 idiots", "Hate Story", "Raaz", "paa")
        var screen_list: ArrayList<Omdb_Values> = ArrayList()

        for (movie in movies) {
            var client = omdbclient(movie)
            client.getBoxOfficeMovies(object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
                    super.onSuccess(statusCode, headers, response)
                    screen_list.add(Omdb_Values.fromJson(response!!)!!)

                }
            }
            )

        }
        return screen_list
    }
}





