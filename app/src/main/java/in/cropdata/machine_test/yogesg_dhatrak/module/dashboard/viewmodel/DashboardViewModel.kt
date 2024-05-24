package `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.viewmodel

import `in`.cropdata.machine_test.yogesg_dhatrak.data.repository.DashboardRepository
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.Resource
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.cropdata.machine_test.yogesg_dhatrak.data.model.Platform
import `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.view.DashboardFragment
import `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.view.PlatformState
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.ResourceStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {
    private val _platformList = MutableStateFlow(PlatformState())
    val platformList: StateFlow<PlatformState> = _platformList

    init {
        getPlatformList()
            .launchIn(viewModelScope)
    }

    private fun getPlatformList() = flow {
        emit(ResourceStatus.Loading())
        try {
            emit(ResourceStatus.Success(data = repository.getPlatformList()))
        } catch (exception: Exception) {
            emit(
                ResourceStatus.Error(
                    data = null, message = exception.message
                        ?: "Error Occurred!"
                )
            )
        }
    }.onEach {
        when (it) {
            is ResourceStatus.Loading -> {
                _platformList.value = PlatformState(isLoading = true)
            }
            is ResourceStatus.Error -> {

                _platformList.value = PlatformState(error = it.message)
            }

            is ResourceStatus.Success -> {
                _platformList.value = PlatformState(data = it.data)
            }
        }
    }

}
