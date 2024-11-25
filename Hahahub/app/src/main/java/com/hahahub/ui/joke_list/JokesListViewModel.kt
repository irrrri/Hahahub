package com.hahahub.ui.joke_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahahub.data.Joke
import com.hahahub.data.JokeRepository
import com.hahahub.data.JokeSource
import kotlinx.coroutines.*

class JokesListViewModel : ViewModel() {
    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> get() = _jokes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        loadJokes()
    }

    fun loadJokes() {
        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                val localJokes = JokeRepository.getJokes()
                val networkJokes = JokeRepository.getNetworkJokes()
                _jokes.value = localJokes
            } catch (e: Exception) {
                _error.value = "Не удалось загрузить шутки: ${e.localizedMessage}"
                Log.e("JokesListViewModel", "Failed to load jokes: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMoreJokes() {
        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                val networkJokes = JokeRepository.getNetworkJokes()
                val currentList = _jokes.value.orEmpty()
                _jokes.value = currentList
            } catch (e: Exception) {
                _error.value = "Не удалось загрузить дополнительные шутки: ${e.localizedMessage}"
                Log.e("JokesListViewModel", "Failed to load jokes: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addNewJoke(category: String, question: String, answer: String) {
        _error.value = null
        viewModelScope.launch {
            try {
                JokeRepository.addJoke(category, question, answer, JokeSource.LOCAL)
                _jokes.value = JokeRepository.getJokes()
            } catch (e: Exception) {
                _error.value = "Не удалось добавить шутку: ${e.localizedMessage}"
                Log.e("JokesListViewModel", "Failed to add joke: ${e.message}")
            }
        }
    }
}
