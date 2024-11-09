package com.hahahub.ui.joke_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hahahub.databinding.FragmentJokeDetailsBinding
import com.hahahub.data.JokeRepository

class JokeDetailsFragment : Fragment() {

    private var _binding: FragmentJokeDetailsBinding? = null
    private val binding get() = _binding!!

    private var jokeId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jokeId = arguments?.getInt(ARG_JOKE_ID) ?: -1
        val joke = JokeRepository.jokes.find { it.id == jokeId }

        if (joke != null) {
            binding.detailJokeCategory.text = joke.category
            binding.detailJokeQuestion.text = joke.question
            binding.detailJokeAnswer.text = joke.answer
        }
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