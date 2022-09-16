package `in`.cropdata.machine_test.yogesg_dhatrak.data.repository

import `in`.cropdata.machine_test.yogesg_dhatrak.base.BaseRepository
import `in`.cropdata.machine_test.yogesg_dhatrak.data.assembler.PlatformDatabaseAssembler.assemblePlatformDetailsModelFromDB
import `in`.cropdata.machine_test.yogesg_dhatrak.data.assembler.PlatformDatabaseAssembler.deleteAllRecordsFromTable
import `in`.cropdata.machine_test.yogesg_dhatrak.data.assembler.PlatformDatabaseAssembler.savePlatFormDataToDB
import `in`.cropdata.machine_test.yogesg_dhatrak.data.entities.PlatformDetailsModel
import `in`.cropdata.machine_test.yogesg_dhatrak.data.local.AppDatabase
import `in`.cropdata.machine_test.yogesg_dhatrak.data.remote.RetrofitInstance
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.NetworkHelper
import android.content.Context
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val apiHelper: RetrofitInstance,
    private val appDatabase: AppDatabase
) : BaseRepository() {
    /* private var appDatabase: AppDatabase? = null

   init {
        initializeDB(mContext)
    }

    private fun initializeDB(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }*/

    fun fetchPlatformsListFromDB() = flow<List<PlatformDetailsModel>> {
        emit(assemblePlatformDetailsModelFromDB(appDatabase))
    }.flowOn(Dispatchers.IO)


    suspend fun getPlatformsListFromServer(): MutableLiveData<List<PlatformDetailsModel>> {
        return withContext(Dispatchers.IO) {
            val responseModel = MutableLiveData<List<PlatformDetailsModel>>()
            if (networkHelper.isNetworkConnected()) {
                val response = apiHelper.getPlatformService()
                    .getAllPlatforms()
                if (response.isSuccessful) {
                    val result = response.body()
                    Timber.d("response--> $result")
                    result?.let {
                        if (result.status) {
                            savePlatFormDataListToDB(result.data.platforms)
                            responseModel.postValue(result.data.platforms)
                        }
                    }
                } else {
                    throw Exception(errorHandling(response.code().toString()))
                }
            } else {
                throw Exception("No Internet Connection")
            }
            responseModel
        }
    }

    suspend fun savePlatFormDataListToDB(platformsList: List<PlatformDetailsModel>) {
        withContext(Dispatchers.IO) {
            savePlatFormDataToDB(appDatabase, platformsList)
        }
    }

    suspend fun deleteAllRecords() {
        withContext(Dispatchers.IO) {
            deleteAllRecordsFromTable(appDatabase)
        }
    }
}