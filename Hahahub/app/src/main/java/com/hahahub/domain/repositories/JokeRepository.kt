package com.hahahub.domain.repositories

import com.hahahub.domain.models.Joke
import com.hahahub.domain.models.JokeSource

interface JokeRepository {
    suspend fun getJokes(): List<Joke>
    suspend fun getNetworkJokes(): List<Joke>
    suspend fun findJokeById(jokeId: Int): Joke?
    suspend fun addJoke(category: String, question: String, answer: String, source: JokeSource, id: Int? = null)
}