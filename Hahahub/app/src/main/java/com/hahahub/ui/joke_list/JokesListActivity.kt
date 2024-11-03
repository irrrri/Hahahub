package com.hahahub.ui.joke_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hahahub.databinding.ActivityJokesListBinding
import com.hahahub.data.Joke
import com.hahahub.data.JokeRepository
import com.hahahub.ui.joke_details.JokeDetailsActivity
import com.hahahub.ui.joke_list.recycler.adapter.JokeAdapter

class JokesListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJokesListBinding

    private val adapter = JokeAdapter {
        startActivity(JokeDetailsActivity.getInstance(this, it))
    }

    private var jokes: List<Joke> = emptyList()

    companion object {
        private const val JOKES_KEY = "JOKES_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createRecyclerViewList()

        jokes = if (savedInstanceState != null) {
            savedInstanceState.getParcelableArrayList(JOKES_KEY) ?: JokeRepository.jokes
        } else {
            JokeRepository.jokes
        }

        adapter.setNewData(jokes)
    }

    private fun createRecyclerViewList() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(JOKES_KEY, ArrayList(jokes))
    }
}