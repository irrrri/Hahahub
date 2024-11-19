package com.hahahub.ui.add_joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahahub.data.JokeRepository
import kotlinx.coroutines.launch

class AddJokeViewModel : ViewModel() {

    private val _addJokeCompleted = MutableLiveData<Boolean>()
    val addJokeCompleted: LiveData<Boolean> get() = _addJokeCompleted

    fun addNewJoke(category: String, question: String, answer: String) {
        viewModelScope.launch {
            try {
                JokeRepository.addJoke(category, question, answer)
                _addJokeCompleted.value = true
            } catch (e: Exception) {
                _addJokeCompleted.value = false
            }
        }
    }
}
