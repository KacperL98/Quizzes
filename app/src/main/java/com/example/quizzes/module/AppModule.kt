package com.example.quizzes.module

import android.content.Context
import com.example.quizzes.api.QuizApi
import com.example.quizzes.basic.Const.Companion.BASE_URL
import com.example.quizzes.database.AppDatabase
import com.example.quizzes.database.QuizDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideCase(gson: Gson): Retrofit {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    internal fun quizService(retrofit: Retrofit): QuizApi {
        return retrofit.create(QuizApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideListItemDao(postsDatabase: AppDatabase): QuizDao {
        return postsDatabase.listItemDao()

    }
}
