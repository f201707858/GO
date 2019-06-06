package com.example.inseego.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import com.example.inseego.R
import com.example.inseego.Screens
import kotlinx.android.synthetic.main.gridview_item.view.*

import android.widget.TextView
import android.app.Activity
import android.view.LayoutInflater


class Gridview_Adapter(var context: Context, var screen_list: ArrayList<String>) : BaseAdapter() {
    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View? {
        var sView:View? = convertview
        val inflater = (context as Activity).layoutInflater
        sView = inflater.inflate(R.layout.gridview_item, parent, false)
        val tView = sView?.findViewById<Button>(R.id.cam_btn)
        tView?.setText(screen_list.get(position))


        tView?.minimumHeight = 250
        //view.minimumWidth
        return sView
    }

    override fun getItem(position: Int): Any {
        return screen_list.get(position)

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return screen_list.size
    }


}