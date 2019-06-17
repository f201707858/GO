package com.example.inseego.Fragments

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.inseego.R
import com.example.inseego.Fragments.ItemFragment.OnListFragmentInteractionListener
import com.example.inseego.Fragments.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */


class MyItemRecyclerViewAdapter(Group_List: ArrayList<String>, _Context: Context) :
    RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    /*private val mOnClickListener: View.OnClickListener
    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }*/


    var group_list = ArrayList<String>()
    var _context: Context? = null

    init {
        group_list = Group_List
        _context = _Context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grouplist_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = group_list[position]
        holder.group_name.text = item
        holder.group_name.setOnClickListener({})

       /* (_context as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_details, )
            .commit()*/
    }


    override fun getItemCount(): Int {
        if (group_list == null) {
            return 0
        } else {
            return group_list.size
        }
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val group_name = mView.findViewById<TextView>(R.id.group_title)
    }

}

