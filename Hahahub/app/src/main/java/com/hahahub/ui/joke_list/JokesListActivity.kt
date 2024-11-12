package com.hahahub.ui.joke_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hahahub.databinding.ActivityJokesListBinding
import com.hahahub.ui.joke_list.recycler.adapter.JokeAdapter
import com.hahahub.ui.joke_details.JokeDetailsActivity

class JokesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokesListBinding
    private val jokesListViewModel: JokesListViewModel by viewModels()

    private val adapter = JokeAdapter {
        startActivity(JokeDetailsActivity.getInstance(this, it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        jokesListViewModel.jokes.observe(this, Observer { jokes ->
            adapter.setNewData(jokes)
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}