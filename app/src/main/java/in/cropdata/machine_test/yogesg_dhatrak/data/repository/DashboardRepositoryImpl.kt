package `in`.cropdata.machine_test.yogesg_dhatrak.data.repository

import `in`.cropdata.machine_test.yogesg_dhatrak.data.assembler.toPlatformModel
import `in`.cropdata.machine_test.yogesg_dhatrak.data.local.PlatformsDao
import `in`.cropdata.machine_test.yogesg_dhatrak.data.model.Platform
import `in`.cropdata.machine_test.yogesg_dhatrak.data.remote.RetrofitInstance
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val apiHelper: RetrofitInstance,
    private val platformDao: PlatformsDao
) : DashboardRepository {
    override suspend fun getPlatformList(): List<Platform> {
        return withContext(Dispatchers.IO){
            try {
                if(networkHelper.isNetworkConnected()){
                    val tempList = apiHelper.getPlatformService()
                        .getAllPlatforms().data.platforms.map { it.toPlatformModel() }
                    platformDao.deleteAll()
                    platformDao.insertAllPlatforms(tempList)
                    platformDao.getAllPlatforms()
                }else{
                    platformDao.getAllPlatforms()
                }
            } catch (e: Exception) {
                Timber.e("DashboardRepositoryImpl: Exception Caught:${e}")
                platformDao.getAllPlatforms()
            }
        }
    }
}