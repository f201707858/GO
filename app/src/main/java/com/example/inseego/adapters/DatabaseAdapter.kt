package com.example.inseego.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.inseego.R


class DatabaseAdapter(Group_List: ArrayList<String>?, _Context: Context) :
    RecyclerView.Adapter<DatabaseAdapter.ViewHolder>() {

    var group_list: ArrayList<String>? = null
    var _context: Context? = null


    init {
        group_list = Group_List
        _context = _Context


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.group_recycler_viewitem, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = group_list?.get(position)
        holder.group_name.text = item
        holder.group_name.setOnClickListener({})

        /* (_context as FragmentActivity).supportFragmentManager
             .beginTransaction()
             .replace(R.id.fragment_details, )
             .commit()*/
    }


    override fun getItemCount(): Int {
        if (group_list == null || group_list?.size == 0) {
            return 0
        } else {
            return group_list!!.size
        }
    }

    fun swap(updated_list: ArrayList<String>?) {
        if (updated_list != null) {
            group_list?.clear()
            group_list?.addAll(updated_list)
        }
        notifyDataSetChanged()
    }
    fun removeItem(position: Int) {
        group_list?.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, group_list!!.size)
    }
    fun addItem(country: String) {
        group_list?.add(country)
        notifyItemInserted(group_list!!.size)
    }


    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val group_name = mView.findViewById<TextView>(R.id.group_title)
    }
}















