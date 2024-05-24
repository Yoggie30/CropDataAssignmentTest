package `in`.cropdata.machine_test.yogesg_dhatrak.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import `in`.cropdata.machine_test.yogesg_dhatrak.data.model.Platform

@Dao
interface PlatformsDao {

    @Query("SELECT * FROM platforms")
    fun getAllPlatforms() : List<Platform>

    @Query("SELECT * FROM platforms WHERE id = :id")
    fun getPlatformDetail(id: Int): LiveData<Platform>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlatforms(platforms: List<Platform>)

    @Query("DELETE FROM platforms")
    fun deleteAll()
}