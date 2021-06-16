package com.example.quizzes.viewmodel

import androidx.lifecycle.*
import com.example.quizzes.database.QuizDao
import com.example.quizzes.database.QuizEntity
import com.example.quizzes.model.QuizModelData
import com.example.quizzes.model.roommodel.RoomListData
import com.example.quizzes.model.roommodel.RoomModelData
import com.example.quizzes.repository.NetworkRepository
import com.example.quizzes.repository.QuizzesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListQuizViewModel @Inject constructor(
    private val useCases: QuizzesRepository,
    private val listItemDao: QuizDao,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val viewsStatusMutable = MutableLiveData<ViewStatus>()
    val observeViewStatus: LiveData<ViewStatus> = viewsStatusMutable

    fun getQuizList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (networkRepository.isInternetAvailable()) {
                    viewsStatusMutable.postValue(ViewStatus.Loading)
                    val response = useCases.getAllQuizzesApi()
                    if (response.isSuccessful) {
                        val data = response.body()
                        val list = data?.items?.map {
                            RoomModelData(
                                id = it.id,
                                mainPhoto = it.mainPhoto,
                                title = it.title
                            )
                        }
                        insertDataToDB(data, list)
                        viewsStatusMutable.postValue(ViewStatus.Success(list))
                    }
                } else {

                    sendQuizzesFromDB()
                }
            } catch (e: Exception) {
                viewsStatusMutable.postValue(ViewStatus.Error)
            }
        }
    }

    private suspend fun sendQuizzesFromDB() {
        val quizzes = listItemDao.getAll().firstOrNull()
        quizzes?.let {
            viewsStatusMutable.postValue(ViewStatus.Success(it.roomListData?.items))
        }
    }

    private suspend fun insertDataToDB(
        data: QuizModelData?,
        list: List<RoomModelData>?
    ) {
        listItemDao.insert(
            QuizEntity(
                null,
                RoomListData(
                    data?.count ?: 0, list ?: listOf()
                )
            )
        )
    }

    sealed class ViewStatus {
        object Loading : ViewStatus()
        object Error : ViewStatus()
        data class Success(val quizzes: List<RoomModelData>?) : ViewStatus()
    }
}
