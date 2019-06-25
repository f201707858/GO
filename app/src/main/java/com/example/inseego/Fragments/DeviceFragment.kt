package com.example.inseego.Fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.inseego.DataBase.Device_Database
import com.example.inseego.R
import kotlinx.android.synthetic.main.app_bar_main.*
import android.R.string.cancel
import android.content.DialogInterface
import android.text.InputType
import android.widget.EditText
import android.app.AlertDialog
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.widget.Adapter


class DeviceFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    var DeviceContent: Device_Database? = null
    var noDevices: TextView? = null
    var myActivity: Activity? = null
    var getListfromDatabase: ArrayList<String>? = null
    var changed_list: ArrayList<String>? = null
    var add_button: FloatingActionButton? = null
    var m_Text: String = ""
    var deviceAdapter: DeviceAdapter? = null
    var DA: DeviceAdapter? = null
    val myCallback = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(
            viewHolder: RecyclerView.ViewHolder,
            direction: Int
        ) {

            deviceAdapter?.group_list?.removeAt(viewHolder.adapterPosition)
            deviceAdapter?.notifyItemRemoved(viewHolder.adapterPosition)

        }



    }
    var myHelper :ItemTouchHelper? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.devicefragment, container, false)
        activity?.title = "Authorize Devices"
        DeviceContent = Device_Database(getActivity())
        noDevices = view?.findViewById(R.id.NoGroups)
        recyclerView = view.findViewById(R.id.recycler_list)
        add_button = view.findViewById(R.id.add)
        myHelper = ItemTouchHelper(myCallback)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        myActivity = context as Activity
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        myActivity = activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /* DeviceContent?.deleteall()
          display_devices()*/



        if (DeviceContent?.checkSize() as Int > 0) {
            getListfromDatabase = DeviceContent?.queryDBList()
            deviceAdapter = DeviceAdapter(getListfromDatabase as ArrayList<String>, myActivity as Context)
            var mLayoutManager = LinearLayoutManager(activity)
            recyclerView?.layoutManager = mLayoutManager
            recyclerView?.itemAnimator = DefaultItemAnimator()
            recyclerView?.adapter = deviceAdapter


        } else {
            recyclerView?.visibility = View.INVISIBLE
            noDevices?.visibility = View.VISIBLE

        }

        //  DA = display_devices()
        add_button?.setOnClickListener({
            val builder = AlertDialog.Builder(myActivity)
            builder.setTitle("Title")
            val input = EditText(myActivity)
            builder.setView(input)
            builder.setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, which -> m_Text = input.text.toString() })
            Log.d("initial", DeviceContent?.checkSize().toString())
            Log.d("initial list ", DeviceContent?.queryDBList().toString())

            DeviceContent?.storeAsGroup(m_Text)
            Log.d("final", DeviceContent?.checkSize().toString())
            Log.d("final list ", DeviceContent?.queryDBList().toString())
            if (deviceAdapter == null) {
                deviceAdapter = DeviceAdapter(DeviceContent?.queryDBList(), myActivity as Context)
            } else {
                deviceAdapter?.swap(DeviceContent?.queryDBList())
            }
            recyclerView?.adapter = deviceAdapter
            noDevices?.visibility = View.INVISIBLE
            builder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.show()


            /*   DA?.swap(DeviceContent?.queryDBList())
               var mLayoutManager = LinearLayoutManager(activity)
               recyclerView?.layoutManager = mLayoutManager
               recyclerView?.itemAnimator = DefaultItemAnimator()
               recyclerView?.adapter = deviceAdapter*/
            val myHelper = ItemTouchHelper(myCallback)
            myHelper.attachToRecyclerView(recyclerView)

        })














    }

























    fun display_devices(): DeviceAdapter? {
        if (DeviceContent?.checkSize() as Int > 0) {
            noDevices?.visibility = View.INVISIBLE
            getListfromDatabase = DeviceContent?.queryDBList()
            var deviceAdapter = DeviceAdapter(getListfromDatabase as ArrayList<String>, myActivity as Context)
            var mLayoutManager = LinearLayoutManager(activity)
            recyclerView?.layoutManager = mLayoutManager
            recyclerView?.itemAnimator = DefaultItemAnimator()
            recyclerView?.adapter = deviceAdapter


        } else {
            recyclerView?.visibility = View.INVISIBLE
            noDevices?.visibility = View.VISIBLE

        }
        return deviceAdapter
    }











}
