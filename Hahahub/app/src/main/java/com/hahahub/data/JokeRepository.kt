package com.hahahub.data

import kotlinx.coroutines.delay

object JokeRepository {
    private val _jokes = mutableListOf(
        Joke(1, "Holiday", "What does Santa suffer from if he gets stuck in a chimney?", "Claustrophobia!"),
        Joke(2, "Animals", "Why did the scarecrow win an award?", "Because he was outstanding in his field!"),
        Joke(3, "Food", "Why don’t eggs tell jokes?", "Because they might crack up!"),
        Joke(4, "Technology", "Why did the computer go to the doctor?", "Because it had a virus."),
        Joke(5, "Music", "Why couldn’t the bicycle stand up by itself?", "It was two-tired!"),
        Joke(6, "Programming", "Why do programmers prefer dark mode?",
            "Because light attracts bugs! Plus, it’s easier on the eyes, which is essential during long coding sessions."),
        Joke(7, "Space", "How do you organize a space party?",
            "You planet! And make sure you have enough space for everyone to dance around the solar system.")
    )

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

    suspend fun addJoke(category: String, question: String, answer: String) {
        delay(500)
        val id = _jokes.size + 1
        val newJoke = Joke(id, category, question, answer)
        _jokes.add(newJoke)
    }
}