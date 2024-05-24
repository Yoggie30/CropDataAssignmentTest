package `in`.cropdata.machine_test.yogesg_dhatrak.data.assembler

import `in`.cropdata.machine_test.yogesg_dhatrak.data.remote.model.PlatformDTO
import `in`.cropdata.machine_test.yogesg_dhatrak.data.local.AppDatabase
import android.annotation.SuppressLint
import `in`.cropdata.machine_test.yogesg_dhatrak.data.model.Platform
import timber.log.Timber

fun PlatformDTO.toPlatformModel(): Platform {
    return Platform(
        id = this.id,
        name = this.name,
        description = this.description,
        iconURL = this.iconURL
    )
}
/*
object PlatformDatabaseAssembler {
    suspend fun savePlatFormDataToDB(
        appDatabase: AppDatabase?,
        platforms: List<Platform>
    ) {
        if (appDatabase != null) {
            val platformsEntityList = mutableListOf<Platform>()
            platforms.map {
                platformsEntityList.add(
                    Platform(
                        name = it.name,
                        iconURL = it.iconURL,
                        description = it.description
                    )
                )
            }
            appDatabase.platformsDao().insertAllPlatforms(platformsEntityList)
        } else {
            Timber.e("savePlatFormDataToDB-->appDatabase is null")
        }

    }

    fun deleteAllRecordsFromTable(appDatabase: AppDatabase?) {
        val list = appDatabase?.platformsDao()?.getAllPlatforms()
        if (!list.isNullOrEmpty()) {
            appDatabase.platformsDao().deleteAll()
        }

    }

    @SuppressLint("CheckResult")
    fun assemblePlatformDetailsModelFromDB(appDatabase: AppDatabase?): List<Platform> {
        val platformsModelList = mutableListOf<Platform>()
        val list = appDatabase?.platformsDao()?.getAllPlatforms()
        if (!list.isNullOrEmpty()) {
            list.map {
                val model = Platform(
                    id = it.id,
                    name = it.name,
                    iconURL = it.iconURL,
                    description = it.description
                )
                platformsModelList.add(model)
            }
        } else {
            Timber.e("assemblePlatformDetailsModel getAllPlatforms empty")
        }
        return platformsModelList
    }
}*/
