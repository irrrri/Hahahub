package com.hahahub.data

import com.hahahub.data.db.CachedJoke
import com.hahahub.data.db.JokeDao
import com.hahahub.data.db.SavedJoke
import com.hahahub.services.JokeApiClient
import javax.inject.Inject

class JokeRepository @Inject constructor(
    private val jokeDao: JokeDao,
    private val jokeApiClient: JokeApiClient
) {

    companion object {
        private const val CACHE_VALIDITY_PERIOD = 24 * 60 * 60 * 1000
    }

    private suspend fun getSavedJokes(): List<Joke> {
        return jokeDao.getSavedJokes().map { it.toDomain() }
    }

    private suspend fun getCachedJokes(): List<Joke> {
        val validTime = System.currentTimeMillis() - CACHE_VALIDITY_PERIOD
        return jokeDao.getValidCachedJokes(validTime).map { it.toDomain() }
    }

    private suspend fun getValidCachedJokesByTimestamp(): List<Joke> {
        val validTime = System.currentTimeMillis() - CACHE_VALIDITY_PERIOD
        return jokeDao.getValidCachedJokesByTimestamp(validTime).map { it.toDomain() }
    }

    private suspend fun cacheNetworkJokes(networkJokes: List<Joke>) {
        val cachedJokes = networkJokes.map { it.toCached() }
        jokeDao.insertCachedJokes(cachedJokes)
    }

    suspend fun addJoke(category: String, question: String, answer: String, source: JokeSource, id: Int? = null) {
        jokeDao.insertSavedJoke(
            SavedJoke(
                id = id ?: 0,
                category = category,
                question = question,
                answer = answer,
                source = source.value
            )
        )
    }

    suspend fun findJokeById(jokeId: Int): Joke? {
        jokeDao.findSavedJokeById(jokeId)?.let {
            return it.toDomain()
        }

        jokeDao.findCachedJokeById(jokeId)?.let {
            return it.toDomain()
        }

        return null
    }

    suspend fun getJokes(): List<Joke> {
        val savedJokes = getSavedJokes()

        val networkOrCachedJokes = try {
            getNetworkJokes()
        } catch (e: Exception) {
            println("Network unavailable, loading cached jokes")
            emptyList()
        }

        return savedJokes + networkOrCachedJokes
    }

    suspend fun getNetworkJokes(): List<Joke> {
        return try {
            val networkJokes = jokeApiClient.fetchJokes().map { it.toDomain() }

            cacheNetworkJokes(networkJokes)

            networkJokes
        } catch (e: Exception) {
            val cachedJokes = getCachedJokes()

            cachedJokes.ifEmpty {
                throw Exception("No network and no cached jokes available")
            }

            cachedJokes
        }
    }
}