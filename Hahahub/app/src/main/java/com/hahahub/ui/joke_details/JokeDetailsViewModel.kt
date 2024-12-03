package com.hahahub.ui.joke_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahahub.data.Joke
import com.hahahub.data.JokeRepository
import com.hahahub.data.db.JokeDatabase
import com.hahahub.services.JokeApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeDetailsViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
) : ViewModel() {

    private val _joke = MutableLiveData<Joke?>()
    val joke: LiveData<Joke?> get() = _joke

    private val _errorEvent = MutableLiveData<Boolean>()
    val errorEvent: LiveData<Boolean> get() = _errorEvent

    fun loadJoke(jokeId: Int) {
        if (jokeId == -1) {
            _errorEvent.value = true
            return
        }

        viewModelScope.launch {
            try {
                val joke = jokeRepository.findJokeById(jokeId)
                if (joke != null) {
                    _joke.value = joke
                } else {
                    _errorEvent.value = true
                }
            } catch (e: Exception) {
                _errorEvent.value = true
            }
        }
    }
}