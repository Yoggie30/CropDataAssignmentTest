package `in`.cropdata.machine_test.yogesg_dhatrak.data.repository

import `in`.cropdata.machine_test.yogesg_dhatrak.data.model.Platform

interface DashboardRepository{
    suspend fun getPlatformList(): List<Platform>
}
/*
class DashboardRepository @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val apiHelper: RetrofitInstance,
    private val appDatabase: AppDatabase
) : BaseRepository() {

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
}*/
