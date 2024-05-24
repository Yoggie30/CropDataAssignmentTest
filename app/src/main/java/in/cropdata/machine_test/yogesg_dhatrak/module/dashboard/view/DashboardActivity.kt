package `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.view

import `in`.cropdata.machine_test.yogesg_dhatrak.base.BaseActivity
import `in`.cropdata.machine_test.yogesg_dhatrak.databinding.ActivityDashboardBinding
import `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.viewmodel.DashboardViewModel
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {
    private val viewModel: DashboardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //viewModel.deleteAllRecordsFromTable()
    }
}