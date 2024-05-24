package `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.view


import android.annotation.SuppressLint
import `in`.cropdata.machine_test.yogesg_dhatrak.base.BaseFragment
import `in`.cropdata.machine_test.yogesg_dhatrak.databinding.FragmentDashboardBinding
import `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.adapter.PlatformsAdapter
import `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.viewmodel.DashboardViewModel
import `in`.cropdata.machine_test.yogesg_dhatrak.module.detail.PlatformDetailActivity
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.Resource
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.autoCleared
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import `in`.cropdata.machine_test.yogesg_dhatrak.data.model.Platform
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment : BaseFragment(), PlatformsAdapter.PlatformItemListener {

    private var binding: FragmentDashboardBinding by autoCleared()
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: PlatformsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadInitialData()
        Timber.d(TAG)
    }

    private fun setupRecyclerView() {
        val appContext = activity?.applicationContext
        adapter = PlatformsAdapter(this, appContext!!)
        binding.rvPlatforms.apply {
            layoutManager = GridLayoutManager(
                this.context, 2,
                RecyclerView.HORIZONTAL, false
            )
            setHasFixedSize(true)
        }
        binding.rvPlatforms.adapter = adapter
    }

    private fun loadInitialData() {
       // viewModel.deleteAllRecordsFromTable()
        Timber.e("$TAG: Yoggie 1")
        setObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.platformList.collectLatest {
                if(it.isLoading){
                    showProgress()
                }
                if(it.error.isNotBlank()){
                    hideProgress()
                    Timber.e("callPlatformDataService ERROR-->$it.error")
                    showAlert(it.error)
                }
                it.data?.let {platformList->
                    hideProgress()
                    if (platformList.isNotEmpty()) adapter.setItems(ArrayList(platformList))
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    /*private fun getPlatformList() {
        viewModel.getPlatformList()
            .observe(viewLifecycleOwner, Observer { resources ->
                when (resources.status) {
                    Resource.Status.SUCCESS -> {
                        hideProgress()
                        resources.data?.let {
                            viewModel.viewModelScope.launch {
                                it.collect {list->
                                    viewModel.setPlatformList(list)
                                }
                            }
                        }
                    }
                    Resource.Status.ERROR -> {
                        hideProgress()
                        resources.message?.let { it1 ->
                            Timber.e("callPlatformDataService ERROR-->$it1")
                            showAlert(it1)
                        }
                    }
                    Resource.Status.LOADING -> {
                        Timber.e("callPlatformDataService Status-->LOADING")
                        showProgress()
                    }
                }
            })
    }*/

    override fun onClickedPlatform(platform: Platform) {
        val gson = Gson()
        val intent = Intent(requireContext(), PlatformDetailActivity::class.java)
        intent.putExtra("platformData", gson.toJson(platform))
        startActivity(intent)
    }

    companion object {
        const val TAG = "DashboardFragment"
    }


}