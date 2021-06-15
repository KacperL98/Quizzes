package com.example.quizzes.basic

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.example.quizzes.R
import com.example.quizzes.databinding.ActivitySplashBinding
import com.example.quizzes.viewmodel.SplashViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        observeViewModel()
//        openHomeActivitySuccess()
//        openHomeActivityError()
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))

    }

    private fun observeViewModel() {
        viewModel.observeViewState.observe(this) {
            when (it) {
//                SplashViewModel.ViewStatus.Success -> openHomeActivitySuccess()
                SplashViewModel.ViewStatus.NetworkErrorConnection -> showError()
//                is Error -> openHomeActivityError()

            }
        }
    }

     fun openHomeActivitySuccess() {

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            binding.imageViewLottie.playAnimation()
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
    }

     fun openHomeActivityError() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            binding.imageViewLottie.playAnimation()
            finish()
            startActivity(MainActivity.openActivity(this@SplashActivity))
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showError() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> viewModel.loadData() }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }
    companion object {
        fun openActivity(context: Context) = Intent(context, SplashActivity::class.java)
    }
}