package `in`.cropdata.machine_test.yogesg_dhatrak.data.remote

import `in`.cropdata.machine_test.yogesg_dhatrak.data.entities.PlatformResponse
import retrofit2.Response
import retrofit2.http.GET

interface PlatformService {
    @GET("machineTest.json")
    suspend fun getAllPlatforms(): Response<PlatformResponse>
}