package com.hahahub.data

import com.hahahub.services.JokeApiClient
import kotlinx.coroutines.delay

object JokeRepository {
    private val _jokes = mutableListOf(
        Joke(1, "Holiday", "What does Santa suffer from if he gets stuck in a chimney?", "Claustrophobia!", JokeSource.LOCAL),
        Joke(2, "Animals", "Why did the scarecrow win an award?", "Because he was outstanding in his field!", JokeSource.LOCAL),
        Joke(3, "Food", "Why don’t eggs tell jokes?", "Because they might crack up!", JokeSource.LOCAL),
        Joke(4, "Technology", "Why did the computer go to the doctor?", "Because it had a virus.", JokeSource.LOCAL),
        Joke(5, "Music", "Why couldn’t the bicycle stand up by itself?", "It was two-tired!", JokeSource.LOCAL),
        Joke(6, "Programming", "Why do programmers prefer dark mode?",
            "Because light attracts bugs! Plus, it’s easier on the eyes, which is essential during long coding sessions.", JokeSource.LOCAL),
        Joke(7, "Space", "How do you organize a space party?",
            "You planet! And make sure you have enough space for everyone to dance around the solar system.", JokeSource.LOCAL)
    )

    private val jokeApiClient = JokeApiClient()

    suspend fun getJokes(): List<Joke> {
        delay(1000)
        return _jokes
    }

    suspend fun findJokeById(jokeId: Int): Joke? {
        delay(300)
        return _jokes.find { it.id == jokeId }
    }

    suspend fun getSize(): Int {
        delay(200)
        return _jokes.size
    }

    suspend fun addJoke(category: String, question: String, answer: String, source: JokeSource, id: Int? = null) {
        val newId = id ?: (_jokes.size + 1)
        val newJoke = Joke(newId, category, question, answer, source)
        _jokes.add(newJoke)
    }

    suspend fun getNetworkJokes(): List<Joke> {
        val jokes = jokeApiClient.fetchJokes()

        jokes.forEach { networkJoke ->
            addJoke(networkJoke.category, networkJoke.setup, networkJoke.delivery, JokeSource.NETWORK, networkJoke.id)
        }

        return jokes.map { Joke(it.id, it.category, it.setup, it.delivery, JokeSource.NETWORK) }
    }
}