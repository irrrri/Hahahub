package com.hahahub.ui.joke_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hahahub.R
import com.example.hahahub.databinding.FragmentJokesListBinding
import com.hahahub.ui.joke_details.JokeDetailsFragment
import com.hahahub.ui.joke_list.recycler.adapter.JokeAdapter
import com.hahahub.ui.add_joke.AddJokeFragment

class JokesListFragment : Fragment() {

    private var _binding: FragmentJokesListBinding? = null
    private val binding get() = _binding!!

    private val jokesListViewModel: JokesListViewModel by viewModels()

    private val adapter = JokeAdapter { jokeId ->
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, JokeDetailsFragment.newInstance(jokeId))
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.btnAddJoke.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AddJokeFragment())
                .addToBackStack(null)
                .commit()
        }

        jokesListViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        jokesListViewModel.jokes.observe(viewLifecycleOwner) { jokes ->
            if (jokes.isEmpty()) {
                binding.emptyStateText.visibility = View.VISIBLE
            } else {
                binding.emptyStateText.visibility = View.GONE
                adapter.setNewData(jokes)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}