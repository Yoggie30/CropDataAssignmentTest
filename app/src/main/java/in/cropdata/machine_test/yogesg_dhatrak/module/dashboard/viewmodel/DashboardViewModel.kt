package `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.viewmodel

import `in`.cropdata.machine_test.yogesg_dhatrak.base.BaseApplication
import `in`.cropdata.machine_test.yogesg_dhatrak.data.entities.PlatformDetailsModel
import `in`.cropdata.machine_test.yogesg_dhatrak.data.entities.PlatformsEntity
import `in`.cropdata.machine_test.yogesg_dhatrak.data.repository.DashboardRepository
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.Resource
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {
    private val _platformList = MutableLiveData<List<PlatformDetailsModel>>()
    val platformList: LiveData<List<PlatformDetailsModel>>
        get() = _platformList


    fun getPlatformsList() =
        viewModelScope.launch {
            repository.fetchPlatformsListFromDB().collect {
                _platformList.value = it
            }
        }

    fun callPlatformsListAPI() =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = repository.getPlatformsListFromServer()))
            } catch (exception: Exception) {
                emit(
                    Resource.error(
                        data = null, message = exception.message
                            ?: "Error Occurred!"
                    )
                )
            }
        }

    fun savePlatFormDataListToDB(platformsList: List<PlatformDetailsModel>) {
        viewModelScope.launch {
            try {
                repository.savePlatFormDataListToDB(platformsList)
            } catch (e: IOException) {
                Timber.e("error ${e.message.toString()}")
            }
        }
    }

    fun deleteAllRecordsFromTable() {
        viewModelScope.launch {
            try {
                repository.deleteAllRecords()
            } catch (e: IOException) {
                Timber.e("error ${e.message.toString()}")
            }
        }
    }
}
