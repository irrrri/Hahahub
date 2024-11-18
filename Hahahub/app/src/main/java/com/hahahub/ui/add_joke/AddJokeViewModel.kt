package com.hahahub.ui.add_joke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahahub.data.Joke
import com.hahahub.data.JokeRepository
import kotlinx.coroutines.launch

class AddJokeViewModel : ViewModel() {

    fun addNewJoke(category: String, question: String, answer: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            JokeRepository.addJoke(category, question, answer)

            onComplete()
        }
    }
}