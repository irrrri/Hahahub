package com.hahahub.ui.joke_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.hahahub.databinding.ActivityJokeDetailsBinding
import com.hahahub.data.Joke

class JokeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeDetailsBinding
    private val viewModel: JokeDetailsViewModel by viewModels()

    companion object {
        private const val JOKE_ID_EXTRA = "JOKE_ID"

        fun getInstance(context: Context, jokeId: Int): Intent {
            return Intent(context, JokeDetailsActivity::class.java).apply {
                putExtra(JOKE_ID_EXTRA, jokeId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()

        val jokeId = intent.getIntExtra(JOKE_ID_EXTRA, -1)
        viewModel.loadJoke(jokeId)
    }

    private fun observeViewModel() {
        viewModel.joke.observe(this, Observer { joke ->
            if (joke != null) {
                setupJokeData(joke)
            }
        })

        viewModel.errorEvent.observe(this, Observer { isError ->
            if (isError) handleError()
        })
    }

    private fun setupJokeData(joke: Joke) {
        with(binding) {
            detailJokeCategory.text = joke.category
            detailJokeQuestion.text = joke.question
            detailJokeAnswer.text = joke.answer
        }
    }

    private fun handleError() {
        Toast.makeText(this, "Invalid joke data!", Toast.LENGTH_SHORT).show()
        finish()
    }
}