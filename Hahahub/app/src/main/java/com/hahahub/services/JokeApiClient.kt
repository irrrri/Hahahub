package com.hahahub.services

import com.hahahub.data.api.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class JokeApiClient @Inject constructor() {

    private val api: JokeApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(JokeApiService::class.java)
    }

    suspend fun fetchJokes(): List<Joke> = withContext(Dispatchers.IO) {
        try {
            val response = api.getJokes(
                blacklistFlags = "nsfw,religious,political,racist,sexist,explicit",
                type = "twopart",
                amount = 10
            )
            response.jokes
        } catch (e: Exception) {
            emptyList()
        }
    }
}