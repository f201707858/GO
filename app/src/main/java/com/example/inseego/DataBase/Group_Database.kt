package com.example.inseego.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.TextUtils

class Device_Database(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        var TABLE_NAME = "NewDevicesTable"
        var DB_NAME = "DeviceDatabase.db"
        var COLUMN_DEVICES = "DeviceName"
        var DB_VERSION = 1
    }

    var _deviceList = ArrayList<String>()


    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_DEVICES + " TEXT" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }


    //to store device_name
    fun storeAsGroup(title: String?) {
        if (!TextUtils.isEmpty(title)) {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(COLUMN_DEVICES, title)
            db.insert(TABLE_NAME, null, contentValues)
            db.close()

        }
    }


    //to Query(i.e.,get) device_data
    fun queryDBList(): ArrayList<String>? {
        try {
            _deviceList.clear()
            val db = this.readableDatabase
            val query_params = "SELECT * FROM " + TABLE_NAME
            var cSor = db.rawQuery(query_params, null)
            if (cSor.moveToFirst()) {
                do {
                    var device_name: String = cSor.getString(cSor.getColumnIndexOrThrow(COLUMN_DEVICES))

                    _deviceList.add(device_name)
                } while (cSor.moveToNext())
            } else {
                return null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return _deviceList
    }


    /* fun check_device(_devicename: String): Boolean {
          var id: String? = null
          val db = this.readableDatabase
          val query_params = "SELECT * FROM " + TABLE_NAME + " WHERE DeviceName  = '$_devicename'"
          var cSor = db.rawQuery(query_params, null)
          if (cSor.moveToFirst()) {
              do {
                  id = cSor.getString(cSor.getColumnIndexOrThrow(COLUMN_DEVICES))
              } while (cSor.moveToNext())
          } else {
              return false
          }
          return id != null
      }*/


    fun deleteFavourite(_devicename: String) : Boolean{
        var result = false

      //  val query =
            //"SELECT * from " + TABLE_NAME + " WHERE DeviceName  = '$_devicename'"
        val db = this.writableDatabase
        /* db.delete(
             TABLE_NAME, COLUMN_DEVICES + "=" + _devicename,
             null
         )*/
        val cursor = db.rawQuery("SELECT * from " + TABLE_NAME + " where " + COLUMN_DEVICES+" =?", arrayOf(_devicename))
        if (cursor.moveToFirst()) {
            val id =cursor.getString(0)
            db.delete(
                TABLE_NAME, COLUMN_DEVICES + " =?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result

    }

    fun deleteall() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }


    fun checkSize(): Int {
        var counter = 0
        val db = this.readableDatabase
        var query_params = "SELECT * FROM " + TABLE_NAME
        val cSor = db.rawQuery(query_params, null)
        if (cSor.moveToFirst()) {
            do {
                counter = counter + 1
            } while (cSor.moveToNext())
        } else {
            return 0
        }
        return counter
    }

}

