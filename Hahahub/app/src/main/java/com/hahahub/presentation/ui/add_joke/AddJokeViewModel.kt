package com.hahahub.presentation.ui.add_joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahahub.data.repositories.JokeRepositoryImpl
import com.hahahub.domain.models.JokeSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddJokeViewModel @Inject constructor(
    private val jokeRepository: JokeRepositoryImpl
): ViewModel() {

    private val _addJokeCompleted = MutableLiveData<Boolean>()
    val addJokeCompleted: LiveData<Boolean> get() = _addJokeCompleted

    fun addNewJoke(category: String, question: String, answer: String) {
        viewModelScope.launch {
            try {
                jokeRepository.addJoke(category, question, answer, JokeSource.LOCAL)
                _addJokeCompleted.value = true
            } catch (e: Exception) {
                _addJokeCompleted.value = false
            }
        }
    }
}
