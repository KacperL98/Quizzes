package com.example.quizzes.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.quizzes.database.CharacterRepository
import com.example.quizzes.database.ListItemDao
import com.example.quizzes.database.ListItemEntity
import com.example.quizzes.database.Resource
import com.example.quizzes.model3.Root
import com.example.quizzes.repository.QuizzesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
class QuizDetailsViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _id = MutableLiveData<Long>()

    private val resultsMutable = MutableLiveData<Root>()
        val observeResults: LiveData<Root> = resultsMutable
    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val character: LiveData<Resource<ListItemEntity>> = _character


    fun start(id: Long) {
        _id.value = id
    }

}

//@HiltViewModel
//class QuizDetailsViewModel @Inject constructor(
//    private val useCases: QuizzesRepository,
//    private val listItemDao: ListItemDao
//) : ViewModel() {
//
//    private val resultsMutable = MutableLiveData<Root>()
//    val observeResults: LiveData<Root> = resultsMutable
//
//    fun refreshQuestion() {
//        resultsMutable.value = resultsMutable.value
//    }
//
//    fun getDetailsQuiz(id_quizu: Long) {
//        viewModelScope.launch {
//            try {
//                val response = useCases.getDetailsQuiz(id_quizu)
//                Timber.d("XDXDK ${response.body()}")
//                if (response.isSuccessful) {
//                    resultsMutable.postValue(response.body())
//                }
//            } catch (e: Exception) {
//            }
//        }
//    }
//
//   suspend fun getData() = listItemDao.getAll().first()
//    fun updateData(id_quizu: Long, root: Root) {
//        viewModelScope.launch {
//            try {
//                val data = getData()
//                data.root?.items?.onEach { if (it.id == id_quizu) {
//                    it.questions = root.questions
//                } }
////                current?.questions = root.questions
//                listItemDao.insert(data)
//            } catch (e: Exception) {
//            }
//        }
//    }
//}

