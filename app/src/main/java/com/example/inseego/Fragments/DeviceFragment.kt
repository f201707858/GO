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
import com.example.inseego.DataBase.Device_Database
import com.example.inseego.R
import kotlinx.android.synthetic.main.app_bar_main.*
import android.R.string.cancel
import android.content.DialogInterface
import android.text.InputType
import android.app.AlertDialog
import android.content.Intent
import android.graphics.*
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.widget.*
import android.widget.Toast
import com.example.inseego.Screens


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
    private val p = Paint()

    //var myHelper: ItemTouchHelper? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.devicefragment, container, false)
        activity?.title = "Authorize Devices"
        DeviceContent = Device_Database(getActivity())
        noDevices = view?.findViewById(R.id.NoGroups)
        recyclerView = view.findViewById(R.id.recycler_list)
        add_button = view.findViewById(R.id.add)
        var mLayoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = mLayoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()
        add_button?.setOnClickListener({
            alert_Dialog("Device Title", "Ok")
        })
        initSwipe()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onResume() {
        super.onResume()
        getListfromDatabase = DeviceContent?.queryDBList()
        //recyclerView?.invalidate()
        if (getListfromDatabase != null) {
            if (deviceAdapter == null) {
                deviceAdapter = DeviceAdapter(getListfromDatabase as ArrayList<String>, myActivity as Context)
                recyclerView?.adapter = deviceAdapter
            } else {
                deviceAdapter?.notifyDataSetChanged()
            }
        }
        recyclerView?.addOnItemTouchListener(
            RecyclerTouchListener(getContext()!!,
                recyclerView!!, object : ClickListener {
                    override
                    fun onClick(view: View, position: Int) {
                        var intent = Intent(getActivity(), Screens::class.java)
                        startActivity(intent)
                    }

                    override
                    fun onLongClick(view: View, position: Int) {
                        /*  Toast.makeText(
                              getActivity() , "Long press on position :" + position,
                              Toast.LENGTH_LONG
                          ).show()*/
                    }
                })
        )
    }


    fun alert_Dialog(title: String, positive: String): String? {
        val builder = AlertDialog.Builder(myActivity)
        builder.setTitle(title)
        val input = EditText(myActivity)
        builder.setView(input)
        builder.setPositiveButton(positive,
            DialogInterface.OnClickListener { dialog, which ->
                m_Text = input.text.toString()
                DeviceContent?.storeAsGroup(m_Text)
            })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
        builder.show()
        return m_Text
    }

    private fun initSwipe() {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    if (direction == ItemTouchHelper.LEFT) {
                        var item = deviceAdapter!!.group_list!!.get(position)
                        DeviceContent?.deleteFavourite(item)
                        deviceAdapter!!.removeItem(position)

                    } else {
                        removeView()
                        var edit_name: String? = null
                        edit_name = alert_Dialog("Edit Title", "Save")
                        if (edit_name != null) {
                            var item = deviceAdapter!!.group_list!!.get(position)
                            DeviceContent?.deleteFavourite(item)
                            DeviceContent?.storeAsGroup(edit_name)
                            deviceAdapter!!.addItem(edit_name)
                            //  deviceAdapter?.group_list[position] = edit_name
                            // deviceAdapter!!.notifyDataSetChanged()
                            //   (view!!.parent as ViewGroup).addView(view)

                        }

                    }
                }


                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    val icon: Bitmap
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                        val itemView = viewHolder.itemView
                        val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                        val width = height / 3

                        if (dX > 0) {
                            p.color = Color.parseColor("#87CEFA")
                            val background =
                                RectF(itemView.left.toFloat(), itemView.top.toFloat(), dX, itemView.bottom.toFloat())
                            c.drawRect(background, p)
                            icon = BitmapFactory.decodeResource(resources, R.drawable.baseline_edit_black_24)
                            val icon_dest = RectF(
                                itemView.left.toFloat() + width,
                                itemView.top.toFloat() + width,
                                itemView.left.toFloat() + 2 * width,
                                itemView.bottom.toFloat() - width
                            )
                            c.drawBitmap(icon, null, icon_dest, p)
                        } else {
                            p.color = Color.parseColor("#87CEFA")
                            val background = RectF(
                                itemView.right.toFloat() + dX,
                                itemView.top.toFloat(),
                                itemView.right.toFloat(),
                                itemView.bottom.toFloat()
                            )
                            c.drawRect(background, p)
                            icon = BitmapFactory.decodeResource(resources, R.drawable.baseline_delete_black_24)
                            val icon_dest = RectF(
                                itemView.right.toFloat() - 2 * width,
                                itemView.top.toFloat() + width,
                                itemView.right.toFloat() - width,
                                itemView.bottom.toFloat() - width
                            )
                            c.drawBitmap(icon, null, icon_dest, p)
                        }
                    }
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun removeView() {
        if (view!!.parent != null) {
            (view!!.parent as ViewGroup).removeView(view)

        }
    }

}
