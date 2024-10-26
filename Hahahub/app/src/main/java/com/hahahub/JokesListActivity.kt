package com.hahahub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hahahub.databinding.ActivityJokesListBinding
import com.hahahub.data.Joke
import com.hahahub.recycler.JokeAdapter

class JokesListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJokesListBinding

    private val adapter = JokeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createRecyclerViewList()

        val jokes = listOf(
            Joke("Holiday", "What does Santa suffer from if he gets stuck in a chimney?", "Claustrophobia!"),
            Joke("Animals", "Why did the scarecrow win an award?", "Because he was outstanding in his field!"),
            Joke("School", "Why was the math book sad?", "Because it had too many problems."),
            Joke("Food", "Why don’t eggs tell jokes?", "Because they might crack up!"),
            Joke("Technology", "Why did the computer go to the doctor?", "Because it had a virus."),
            Joke("Music", "Why couldn’t the bicycle stand up by itself?", "It was two-tired!"),
            Joke("Science", "Why do chemists like nitrates so much?", "Because they’re cheaper than day rates!")
        )
        adapter.setNewData(jokes)
    }

    private fun createRecyclerViewList() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)
    }
}