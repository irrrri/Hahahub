package com.hahahub.presentation.ui.joke_list.recycler

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.hahahub.databinding.JokeItemBinding
import com.hahahub.domain.models.Joke
import com.hahahub.domain.constants.Constants

class JokeViewHolder(
    val binding: JokeItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        bindCategory(joke.category)
        bindQuestion(joke.question)
        bindAnswer(joke.answer)
        bindSource(joke.source.value)
    }

    fun bind(diffBundle: Bundle) {
        diffBundle.keySet().forEach { key ->
            val value = diffBundle.getString(key)
            if (value != null) {
                when (key) {
                    Constants.CATEGORY_KEY -> bindCategory(value)
                    Constants.QUESTION_KEY -> bindQuestion(value)
                    Constants.ANSWER_KEY -> bindAnswer(value)
                    Constants.SOURCE_KEY -> bindSource(value)
                }
            }
        }
    }

    private fun setTextForView(key: String, value: String) {
        when (key) {
            Constants.CATEGORY_KEY -> bindCategory(value)
            Constants.QUESTION_KEY -> bindQuestion(value)
            Constants.ANSWER_KEY -> bindAnswer(value)
            Constants.SOURCE_KEY -> bindSource(value)
        }
    }

    private fun bindCategory(text: String) {
        binding.jokeCategory.text = text
    }

    private fun bindQuestion(text: String) {
        binding.jokeQuestion.text = text
    }

    private fun bindAnswer(text: String) {
        binding.jokeAnswer.text = text
    }

    private fun bindSource(text: String) {
        binding.jokeSource.text = text
    }
}