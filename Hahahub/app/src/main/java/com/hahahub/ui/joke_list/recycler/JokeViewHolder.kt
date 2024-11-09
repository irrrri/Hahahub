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
                setTextForView(key, value)
            }
        }
    }

    private fun setTextForView(key: String, value: String) {
        when (key) {
            Constants.CATEGORY_KEY -> binding.jokeCategory.text = value
            Constants.QUESTION_KEY -> binding.jokeQuestion.text = value
            Constants.ANSWER_KEY -> binding.jokeAnswer.text = value
        }
    }
}