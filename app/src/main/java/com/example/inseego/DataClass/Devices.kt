package com.example.inseego.DataClass

import java.sql.Timestamp

data class Devices(
    var id: Int = 0,
    var cameragroup_name: String? = null,
    var createdat: Timestamp? = null
)

data class Cameras(
    var camera_id: Int = 0,
    var camera_name: String? = null,
    var cam_createdat: Timestamp? = null,
    var cam_updatedat: Timestamp? = null,
    var camera_source: String? = null,
    var camera_group: Int? = null

)
