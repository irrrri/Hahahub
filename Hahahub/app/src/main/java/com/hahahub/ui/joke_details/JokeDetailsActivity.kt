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

    private var jokeId: Int = -1

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
        handleExtra()
    }

    private fun handleExtra() {
        jokeId = intent.getIntExtra(JOKE_ID_EXTRA, -1)

        if (jokeId == -1) {
            handleError()
        } else {
            val joke = JokeRepository.findJokeById(jokeId)

            if (joke != null) {
                setupJokeData(joke)
            } else {
                handleError()
            }
        }
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