package com.hahahub.ui.joke_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahahub.data.Joke
import com.hahahub.data.JokeRepository
import kotlinx.coroutines.*

class JokesListViewModel : ViewModel() {
    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> get() = _jokes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        loadJokes()
    }

    fun loadJokes() {
        _isLoading.value = true
        viewModelScope.launch {
            delay(2000)
            _jokes.value = JokeRepository.jokes
            Log.d("JokesListViewModel", "Jokes received 2: ${_jokes.value}")
            _isLoading.value = false
        }
    }

    fun addNewJoke(category: String, question: String, answer: String) {
        JokeRepository.addJoke(category, question, answer)
        _jokes.value = JokeRepository.jokes
    }
}
