package `in`.cropdata.machine_test.yogesg_dhatrak.data.remote


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://assessment.sgp1.digitaloceanspaces.com/android/test/"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(UnsafeOkHttpClient.getUnsafeOkHttpClient().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getPlatformService(): PlatformService {
        return retrofit.create(PlatformService::class.java)
    }


}