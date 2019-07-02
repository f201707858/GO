package com.example.inseego.RetrofitServices

import com.example.inseego.DataClass.Devices
import retrofit2.Call
import retrofit2.http.GET

interface DevicesService {
    //Defines functions mapping to end services urls
    @GET("api/cameragroup")
    fun getDevicesList() : Call<List<Devices>>


}



