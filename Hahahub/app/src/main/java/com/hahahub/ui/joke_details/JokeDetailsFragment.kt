package com.hahahub.ui.joke_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hahahub.databinding.FragmentJokeDetailsBinding
import com.hahahub.data.Joke
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokeDetailsFragment : Fragment() {

    private var _binding: FragmentJokeDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JokeDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        val jokeId = arguments?.getInt(ARG_JOKE_ID) ?: -1
        viewModel.loadJoke(jokeId)
    }

    private fun observeViewModel() {
        viewModel.joke.observe(viewLifecycleOwner, Observer { joke ->
            if (joke != null) {
                setupJokeData(joke)
            }
        })

        viewModel.errorEvent.observe(viewLifecycleOwner, Observer { isError ->
            if (isError) {
                handleError()
            }
        })
    }

    private fun setupJokeData(joke: Joke) {
        with(binding) {
            detailJokeCategory.text = joke.category
            detailJokeQuestion.text = joke.question
            detailJokeAnswer.text = joke.answer
            detailJokeSource.text = joke.source.value
        }
    }

    private fun handleError() {
        Toast.makeText(requireContext(), "Invalid joke data!", Toast.LENGTH_SHORT).show()
        requireActivity().onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_JOKE_ID = "joke_id"

        fun newInstance(jokeId: Int) = JokeDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_JOKE_ID, jokeId)
            }
        }
    }
}