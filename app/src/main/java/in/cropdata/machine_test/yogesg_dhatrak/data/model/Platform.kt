package `in`.cropdata.machine_test.yogesg_dhatrak.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "platforms")
data class Platform(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val iconURL: String,
    val description: String
)