package `in`.cropdata.machine_test.yogesg_dhatrak.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import `in`.cropdata.machine_test.yogesg_dhatrak.data.model.Platform
import `in`.cropdata.machine_test.yogesg_dhatrak.data.remote.model.PlatformDTO

@Database(entities = [Platform::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun platformsDao(): PlatformsDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "cropdata ")
                .fallbackToDestructiveMigration()
                .build()
    }

}