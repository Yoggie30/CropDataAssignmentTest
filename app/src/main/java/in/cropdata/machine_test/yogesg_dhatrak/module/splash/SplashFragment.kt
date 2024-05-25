package `in`.cropdata.machine_test.yogesg_dhatrak.module.splash

import `in`.cropdata.machine_test.yogesg_dhatrak.R
import `in`.cropdata.machine_test.yogesg_dhatrak.base.BaseFragment
import `in`.cropdata.machine_test.yogesg_dhatrak.databinding.FragmentSplashBinding
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.autoCleared
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : BaseFragment() {
    private var binding: FragmentSplashBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler()
    }

    private fun handler() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(
                R.id.action_splashFragment_to_dashboardFragment
            )
        }, SPLASH_TIME_OUT)

    }

    companion object {
        const val TAG = "SplashFragment"
        const val SPLASH_TIME_OUT: Long = 3000 // 3 sec
    }
}