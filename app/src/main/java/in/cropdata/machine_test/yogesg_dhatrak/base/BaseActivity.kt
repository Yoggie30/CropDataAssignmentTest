package `in`.cropdata.machine_test.yogesg_dhatrak.base

import `in`.cropdata.machine_test.yogesg_dhatrak.utils.CustomProgressDialogue
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.CustomProgressManager
import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    private var activity: Activity? = null
    var isProgressVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    open fun hideProgress() {
        if (isProgressVisible) {
            CustomProgressManager.dialog.dismiss()
            isProgressVisible = false
        }
    }

    open fun showProgress() {
        if (!isProgressVisible) {
            CustomProgressManager.dialog.show(
                this.supportFragmentManager,
                CustomProgressDialogue.TAG
            )
            isProgressVisible = true
        }
    }
}