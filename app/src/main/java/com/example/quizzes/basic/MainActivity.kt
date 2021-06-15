package com.example.quizzes.basic

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.example.quizzes.R
import com.example.quizzes.databinding.ActivityMainBinding
import com.example.quizzes.gone
import com.example.quizzes.repository.ConnectivityRepository
import com.example.quizzes.show
import com.example.quizzes.viewmodel.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ConnectivityRepository.ConnectionReceiverListener {

    @Inject
    lateinit var connectivityRepository: ConnectivityRepository

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        registerConnectivityReceiver()
    }

    private fun observeViewModel() {
        viewModel.observeIsStillNetworkUnAvailable.observe(this) {
            if (it) {
                backToSplash()
            }
        }
    }

    private fun backToSplash() {
        finish()
        val intent = SplashActivity.openActivity(this)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
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
        viewModel.checkStatusNetwork(isConnected)
    }

    companion object {
        fun openActivity(context: Context) = Intent(context, SplashActivity::class.java)
    }

//    private fun showError() {
//        MaterialAlertDialogBuilder(this)
//            .setTitle(R.string.cant_download_dialog_title)
//            .setMessage(getString(R.string.cant_download_dialog_message))
//            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> viewModel.loadData() }
//            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
//            .create()
//            .apply { setCanceledOnTouchOutside(false) }
//            .show()
//    }
}