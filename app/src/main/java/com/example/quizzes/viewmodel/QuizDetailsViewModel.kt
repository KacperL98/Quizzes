package com.example.quizzes.viewmodel

import androidx.lifecycle.*
import com.example.quizzes.database.QuizDao
import com.example.quizzes.model.QuizDetailsDataModel
import com.example.quizzes.repository.NetworkRepository
import com.example.quizzes.repository.QuizzesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizDetailsViewModel @Inject constructor(
    private val useCases: QuizzesRepository,
    private val listItemDao: QuizDao,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val resultsMutable = MutableLiveData<QuizDetailsDataModel>()
    val observeResults: LiveData<QuizDetailsDataModel> = resultsMutable

    fun refreshQuestion() {
        resultsMutable.value = resultsMutable.value
    }

    private suspend fun getQuizzesDB() = listItemDao.getAll().first()

    private fun addQuestionQuizToDB(idQuiz: Long, root: QuizDetailsDataModel?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = getQuizzesDB()
                data.roomListData?.items?.onEach {
                    if (it.id == idQuiz && root != null) {
                        it.questions = root.questions
                    }
                }
                listItemDao.insert(data)
            } catch (e: Exception) {

            }
        }
    }

    fun getDetailsQuiz(idQuiz: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (networkRepository.isInternetAvailable()) {
                    val response = useCases.getDetailsQuiz(idQuiz)
                    if (response.isSuccessful) {
                        resultsMutable.postValue(response.body())
                        addQuestionQuizToDB(idQuiz, response.body())
                    }
                } else {
                    getQuizDetailsDB(idQuiz)
                }
            } catch (e: Exception) {
            }
        }
    }

    private suspend fun getQuizDetailsDB(idQuiz: Long) {
        val data = getQuizzesDB()
        val currentQuiz = data.roomListData?.items?.firstOrNull { it.id == idQuiz }
        resultsMutable.postValue(
            QuizDetailsDataModel(
                id = currentQuiz?.id ?: 0,
                title = currentQuiz?.title ?: "",
                questions = currentQuiz?.questions ?: listOf(),
                mainPhoto = currentQuiz?.mainPhoto
            )
        )
    }

}