package com.hahahub.ui.joke_list.recycler

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.hahahub.databinding.JokeItemBinding
import com.hahahub.data.Joke
import com.hahahub.ui.constants.Constants

class JokeViewHolder(
    val binding: JokeItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        setTextForView(Constants.CATEGORY_KEY, joke.category)
        setTextForView(Constants.QUESTION_KEY, joke.question)
        setTextForView(Constants.ANSWER_KEY, joke.answer)
    }

    fun bind(diffBundle: Bundle) {
        diffBundle.keySet().forEach { key ->
            val value = diffBundle.getString(key)
            if (value != null) {
                when (key) {
                    Constants.CATEGORY_KEY -> bindCategory(value)
                    Constants.QUESTION_KEY -> bindQuestion(value)
                    Constants.ANSWER_KEY -> bindAnswer(value)
                }
            }
        }
    }

    private fun setTextForView(key: String, value: String) {
        when (key) {
            Constants.CATEGORY_KEY -> bindCategory(value)
            Constants.QUESTION_KEY -> bindQuestion(value)
            Constants.ANSWER_KEY -> bindAnswer(value)
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
}