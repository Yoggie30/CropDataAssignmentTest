package `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.view

import `in`.cropdata.machine_test.yogesg_dhatrak.data.model.Platform

data class PlatformState(
    val isLoading:Boolean=false,
    val error:String="",
    val data:List<Platform>?=null
)
