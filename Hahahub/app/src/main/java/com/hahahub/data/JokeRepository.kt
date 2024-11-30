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
    suspend fun getSavedJokes(): List<Joke> {
        return jokeDao.getSavedJokes().map {
            Joke(it.id, it.category, it.question, it.answer, JokeSource.fromValue(it.source))
        }
    }

    suspend fun getCachedJokes(): List<Joke> {
        val validTime = System.currentTimeMillis() - 24 * 60 * 60 * 1000
        return jokeDao.getValidCachedJokes(validTime).map {
            Joke(it.id, it.category, it.question, it.answer, JokeSource.fromValue(it.source))
        }
    }

    suspend fun getValidCachedJokesByTimestamp(): List<Joke> {
        val validTime = System.currentTimeMillis() - 24 * 60 * 60 * 1000
        return jokeDao.getValidCachedJokesByTimestamp(validTime).map {
            Joke(it.id, it.category, it.question, it.answer, JokeSource.fromValue(it.source))
        }
    }

    suspend fun addJoke(category: String, question: String, answer: String, source: JokeSource, id: Int? = null) {
        val newJoke = Joke(id ?: 0, category, question, answer, source)
        jokeDao.insertSavedJoke(
            SavedJoke(
                id = newJoke.id,
                category = newJoke.category,
                question = newJoke.question,
                answer = newJoke.answer,
                source = newJoke.source.value
            )
        )
    }

    suspend fun getNetworkJokes(): List<Joke> {
        return try {
            val networkJokes = jokeApiClient.fetchJokes()

            val cachedJokes = networkJokes.map {
                CachedJoke(
                    id = 0,
                    category = it.category,
                    question = it.setup,
                    answer = it.delivery,
                    source = JokeSource.CACHED.value,
                    timestamp = System.currentTimeMillis()
                )
            }
            jokeDao.clearCachedJokes()
            jokeDao.insertCachedJokes(cachedJokes)

            val newJokes = getValidCachedJokesByTimestamp()

            newJokes.map {
                Joke(it.id, it.category, it.question, it.answer, JokeSource.NETWORK)
            }
        } catch (e: Exception) {
            val cachedJokes = getCachedJokes()

            if (cachedJokes.isNotEmpty()) {
                cachedJokes.map {
                    Joke(it.id, it.category, it.question, it.answer, JokeSource.CACHED)
                }
            } else {
                throw Exception("No network and no cached jokes available")
            }
        }
    }

    suspend fun getJokes(): List<Joke> {
        val savedJokes = getSavedJokes()

        val networkOrCachedJokes = try {
            getNetworkJokes()
        } catch (e: Exception) {
            println("Network unavailable, loading cached jokes")
            emptyList()
        }

        val cachedJokes = getCachedJokes()

        return savedJokes + cachedJokes
    }

    suspend fun findJokeById(jokeId: Int): Joke? {
        jokeDao.findSavedJokeById(jokeId)?.let {
            return Joke(it.id, it.category, it.question, it.answer, JokeSource.fromValue(it.source))
        }

        jokeDao.findCachedJokeById(jokeId)?.let {
            return Joke(it.id, it.category, it.question, it.answer, JokeSource.CACHED)
        }

        return null
    }
}