package `in`.cropdata.machine_test.yogesg_dhatrak.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class PlatformDTO(
    val id: Int = 0,
    val name: String,
    val iconURL: String,
    val description: String
)