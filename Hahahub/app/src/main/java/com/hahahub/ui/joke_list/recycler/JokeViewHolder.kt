package com.hahahub.ui.joke_list.recycler

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.hahahub.databinding.JokeItemBinding
import com.hahahub.data.Joke
import com.hahahub.data.Constants

class JokeViewHolder(
    val binding: JokeItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        binding.jokeCategory.text = joke.category
        binding.jokeQuestion.text = joke.question
        binding.jokeAnswer.text = joke.answer
    }

    fun bind(diffBundle: Bundle) {
        diffBundle.keySet().forEach { key ->
            when (key) {
                Constants.CATEGORY_KEY -> binding.jokeCategory.text = diffBundle.getString(Constants.CATEGORY_KEY)
                Constants.QUESTION_KEY -> binding.jokeQuestion.text = diffBundle.getString(Constants.QUESTION_KEY)
                Constants.ANSWER_KEY -> binding.jokeAnswer.text = diffBundle.getString(Constants.ANSWER_KEY)
            }
        }
    }
}