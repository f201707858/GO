package com.example.inseego.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.example.inseego.R

import android.app.Activity
import android.widget.*
import com.squareup.picasso.Picasso
import omdb.Omdb_Values


class Gridview_Adapter(var context: Context, var screen_list: ArrayList< Omdb_Values>) : BaseAdapter() {

    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View? {
        var omdb2 : Omdb_Values = getItem(position)
        var sView:View? = convertview
        val inflater = (context as Activity).layoutInflater
        sView = inflater.inflate(R.layout.monitoring_item, parent, false)

        val tView = sView?.findViewById<TextView>(R.id.title)
        tView?.setText(omdb2.get_title())
        tView?.minimumHeight = 50

        val pView = sView?.findViewById<ImageView>(R.id.poster)
        Picasso.with(context).load(omdb2.get_PosterUrl()).into(pView)
        pView?.minimumHeight = 250


        return sView
    }

    override fun getItem(position: Int): Omdb_Values {
        return screen_list.get(position)

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return screen_list.size
    }


}