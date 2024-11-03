package com.hahahub.ui.joke_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hahahub.databinding.ActivityJokeDetailsBinding
import com.hahahub.data.Joke
import com.hahahub.data.JokeRepository

class JokeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeDetailsBinding

    private var jokePosition: Int = -1

    companion object {

        private const val JOKE_POSITION_EXTRA = "JOKE_POSITION"

        fun getInstance(context: Context, personPosition: Int): Intent {
            return Intent(context, JokeDetailsActivity::class.java).apply {
                putExtra(JOKE_POSITION_EXTRA, personPosition)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleExtra()
    }

    private fun handleExtra() {
        val jokePosition = intent.getIntExtra(JOKE_POSITION_EXTRA, -1)

        if (jokePosition == -1) {
            handleError()
        } else {
            val joke = JokeRepository.jokes.getOrNull(jokePosition)

            if (joke != null) {
                setupJokeData(joke)
            } else {
                handleError()
            }
        }
    }

    private fun setupJokeData(joke: Joke) {
        with (binding) {
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