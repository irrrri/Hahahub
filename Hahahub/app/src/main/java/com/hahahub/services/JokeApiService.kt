package com.hahahub.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response

data class JokeResponse(
    val error: Boolean,
    val amount: Int,
    val jokes: List<Joke>
)

data class Joke(
    val category: String,
    val type: String,
    val setup: String,
    val delivery: String,
    val flags: Flags,
    val safe: Boolean,
    val id: Int,
    val lang: String
)

data class Flags(
    val nsfw: Boolean,
    val religious: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val sexist: Boolean,
    val explicit: Boolean
)

interface JokeApiService {
    @GET("joke/Any")
    suspend fun getJokes(
        @Query("blacklistFlags") blacklistFlags: String,
        @Query("type") type: String,
        @Query("amount") amount: Int
    ): JokeResponse
}

object RetrofitInstance {

    private const val BASE_URL = "https://v2.jokeapi.dev/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: JokeApiService by lazy {
        retrofit.create(JokeApiService::class.java)
    }
}

suspend fun fetchJokes(): List<Joke> {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.getJokes(
                blacklistFlags = "nsfw,religious,political,racist,sexist,explicit",
                type = "twopart",
                amount = 10
            )

            response.jokes
        } catch (e: Exception) {
            emptyList<Joke>()
        }
    }
}
