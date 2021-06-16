package com.example.quizzes.ui.activity

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.quizzes.R
import com.example.quizzes.databinding.ActivityMainBinding
import com.example.quizzes.extension.gone
import com.example.quizzes.repository.ConnectivityRepository
import com.example.quizzes.extension.show
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ConnectivityRepository.ConnectionReceiverListener {

    @Inject
    lateinit var connectivityRepository: ConnectivityRepository
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerConnectivityReceiver()
        navFragment()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun showOrHideBanner(isNetworkConnection: Boolean?) {
        if (isNetworkConnection == true) {
            binding.appbar.bannerOfflineMode.banner.gone()
        } else binding.appbar.bannerOfflineMode.banner.show()
    }

    override fun onResume() {
        super.onResume()
        connectivityRepository.connectivityReceiverListener = this
    }

    override fun onPause() {
        super.onPause()
        connectivityRepository.connectivityReceiverListener = null
    }

    private fun registerConnectivityReceiver() {
        registerReceiver(
            connectivityRepository,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showOrHideBanner(isConnected)
    }

    private fun navFragment(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
    }
}