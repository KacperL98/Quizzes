package com.example.quizzes.database

import com.example.quizzes.repository.QuizzesRepository
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: QuizzesRepository,
    private val localDataSource: ListItemDao
) {

    fun getCharacter(id: Long) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getDetailsQuiz(id) })
//        saveCallResult = { localDataSource.insert(it) }


    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getAllQuizzes() })
//        saveCallResult = { localDataSource.insertAll(it.items) }

}