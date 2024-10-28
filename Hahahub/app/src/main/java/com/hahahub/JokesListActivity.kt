package com.hahahub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hahahub.databinding.ActivityJokesListBinding
import com.hahahub.data.Joke
import com.hahahub.recycler.adapter.JokeAdapter

class JokesListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJokesListBinding

    private val adapter = JokeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createRecyclerViewList()

        val jokes = listOf(
            Joke(1, "Holiday", "What does Santa suffer from if he gets stuck in a chimney?", "Claustrophobia!"),
            Joke(2, "Animals", "Why did the scarecrow win an award?", "Because he was outstanding in his field!"),
            Joke(3, "Food", "Why don’t eggs tell jokes?", "Because they might crack up!"),
            Joke(4, "Technology", "Why did the computer go to the doctor?", "Because it had a virus."),
            Joke(5, "Music", "Why couldn’t the bicycle stand up by itself?", "It was two-tired!"),
            Joke(6, "Programming", "Why do programmers prefer dark mode?",
            "Because light attracts bugs! Plus, it’s easier on the eyes, which is essential during long coding sessions."),
            Joke(7, "Space", "How do you organize a space party?",
            "You planet! And make sure you have enough space for everyone to dance around the solar system.")
        )
        adapter.setNewData(jokes)
    }

    private fun createRecyclerViewList() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)
    }
}