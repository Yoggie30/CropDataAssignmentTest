package `in`.cropdata.machine_test.yogesg_dhatrak.data.local

import `in`.cropdata.machine_test.yogesg_dhatrak.data.entities.PlatformsEntity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlatformsDao {

    @Query("SELECT * FROM platforms")
    fun getAllPlatforms() : List<PlatformsEntity>

    @Query("SELECT * FROM platforms WHERE id = :id")
    fun getPlatformDetail(id: Int): LiveData<PlatformsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlatforms(platforms: List<PlatformsEntity>)

    @Query("DELETE FROM platforms")
    fun deleteAll()



}