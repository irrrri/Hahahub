package com.hahahub.ui.joke_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hahahub.data.Joke
import com.hahahub.data.JokeRepository

class JokeDetailsViewModel : ViewModel() {

    private val _joke = MutableLiveData<Joke?>()
    val joke: LiveData<Joke?> get() = _joke

    private val _errorEvent = MutableLiveData<Boolean>()
    val errorEvent: LiveData<Boolean> get() = _errorEvent

    fun loadJoke(jokeId: Int) {
        if (jokeId == -1) {
            _errorEvent.value = true
        } else {
            val joke = JokeRepository.findJokeById(jokeId)
            if (joke != null) {
                _joke.value = joke
            } else {
                _errorEvent.value = true
            }
        }
    }
}