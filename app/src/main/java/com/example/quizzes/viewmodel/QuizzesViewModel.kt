package com.example.quizzes.viewmodel

import androidx.lifecycle.*
import com.example.quizzes.database.ListItemDao
import com.example.quizzes.database.ListItemEntity
import com.example.quizzes.model.Item
import com.example.quizzes.model.Room2
import com.example.quizzes.model.RoomData
import com.example.quizzes.repository.QuizzesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class QuizzesViewModel @Inject constructor(
    private val useCases: QuizzesRepository,
    private val listItemDao: ListItemDao
) : ViewModel() {

    private val resultsMutable = MutableLiveData<List<Item>>()
    val observeResults: LiveData<List<Item>> = resultsMutable

    fun getPost(){

        viewModelScope.launch {
            Timber.d("XD5 ${listItemDao.getAll().first()}")
            try {
                val response = useCases.getAllQuizzes()
//dodac zaleznosc o tym czy dane maja byc pobrane z rooma czy api
                if (response.isSuccessful) {
                    val data = response.body()
                    val list = data?.items?.map { Room2(id = it.id, mainPhoto = it.mainPhoto, title = it.title ) }
                    listItemDao.insert(ListItemEntity(null, RoomData(data?.count?:0, list?: listOf() )))
                    resultsMutable.postValue(response.body()?.items)
                }
            } catch (e: Exception) {
            }
        }
    }
}

