package com.hahahub.ui.joke_list.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hahahub.data.Joke
import com.hahahub.data.JokeRepository

class JokesListViewModel : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> get() = _jokes

    init {
        loadJokes()
    }

    private fun loadJokes() {
        _jokes.value = JokeRepository.jokes
    }

    fun updateJokes(newJokes: List<Joke>) {
        _jokes.value = newJokes
    }
}
