package com.example.quizzes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzes.repository.NetworkRepository
import com.example.quizzes.repository.QuizzesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val listItemRepository: QuizzesRepository,
    private val networkRepository: NetworkRepository
) :
    ViewModel() {

    private val viewStateMutable = MutableLiveData<ViewStatus>()
    val observeViewState: LiveData<ViewStatus> = viewStateMutable

//    init {
//        if (isNetworkConnection()) {
//            loadData()
//        } else {
//            viewStateMutable.postValue(ViewStatus.NetworkErrorConnection)
//        }
//    }

    private fun isNetworkConnection() = networkRepository.isInternetAvailable()

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val listFromApi = listItemRepository.getAllQuizzes()
//                if (listFromApi.data) {
//                    viewStateMutable.postValue(ViewStatus.Success)
//                } else {
//                    viewStateMutable
//                }
            } catch (e: Exception) {
                if (e is SocketTimeoutException) {
                    loadData()
                } else {
                    viewStateMutable
                }
            }
        }

    }

    sealed class ViewStatus {
        object Success : ViewStatus()
        object NetworkErrorConnection : ViewStatus()
    }

}