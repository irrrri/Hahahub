package com.hahahub.presentation.ui.joke_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahahub.domain.models.Joke
import com.hahahub.data.repositories.JokeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class JokesListViewModel @Inject constructor(
    private val jokeRepository: JokeRepositoryImpl
) : ViewModel() {
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
                _jokes.value = jokeRepository.getJokes()
            } catch (e: Exception) {
                _error.value = "Не удалось загрузить шутки: ${e.localizedMessage}"
                Log.e("JokesListViewModel", "Failed to load jokes: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMoreJokes() {
        if (isLoading.value == true) return

        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                val networkJokes = jokeRepository.getNetworkJokes()
                val currentList = _jokes.value.orEmpty()
                _jokes.value = currentList + networkJokes
            } catch (e: Exception) {
                _error.value = "Не удалось загрузить дополнительные шутки: ${e.localizedMessage}"
                Log.e("JokesListViewModel", "Failed to load jokes: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}

