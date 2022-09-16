package `in`.cropdata.machine_test.yogesg_dhatrak.base

import `in`.cropdata.machine_test.yogesg_dhatrak.BuildConfig
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}