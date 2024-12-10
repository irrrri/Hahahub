package com.hahahub.domain.services

import com.hahahub.data.api.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApiService {
    @GET("joke/Any")
    suspend fun getJokes(
        @Query("blacklistFlags") blacklistFlags: String,
        @Query("type") type: String,
        @Query("amount") amount: Int
    ): JokeResponse
}
