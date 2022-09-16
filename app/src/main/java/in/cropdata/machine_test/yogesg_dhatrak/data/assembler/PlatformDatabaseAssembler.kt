package `in`.cropdata.machine_test.yogesg_dhatrak.data.assembler

import `in`.cropdata.machine_test.yogesg_dhatrak.data.entities.PlatformDetailsModel
import `in`.cropdata.machine_test.yogesg_dhatrak.data.entities.PlatformsEntity
import `in`.cropdata.machine_test.yogesg_dhatrak.data.local.AppDatabase
import android.annotation.SuppressLint
import timber.log.Timber

object PlatformDatabaseAssembler {
    suspend fun savePlatFormDataToDB(
        appDatabase: AppDatabase?,
        platforms: List<PlatformDetailsModel>
    ) {
        if (appDatabase != null) {
            val platformsEntityList = mutableListOf<PlatformsEntity>()
            platforms.map {
                platformsEntityList.add(
                    PlatformsEntity(
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
    fun assemblePlatformDetailsModelFromDB(appDatabase: AppDatabase?): List<PlatformDetailsModel> {
        val platformsModelList = mutableListOf<PlatformDetailsModel>()
        val list = appDatabase?.platformsDao()?.getAllPlatforms()
        if (!list.isNullOrEmpty()) {
            list.map {
                val model = PlatformDetailsModel(
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
}