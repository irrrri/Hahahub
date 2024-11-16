package com.hahahub.ui.add_joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hahahub.databinding.FragmentAddJokeBinding
import com.hahahub.ui.joke_list.JokesListViewModel

class AddJokeFragment : Fragment() {

    private var _binding: FragmentAddJokeBinding? = null
    private val binding get() = _binding!!

    private val jokesListViewModel: JokesListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddJoke.setOnClickListener {
            val category = binding.editCategory.text.toString()
            val question = binding.editQuestion.text.toString()
            val answer = binding.editAnswer.text.toString()

            if (category.isNotBlank() && question.isNotBlank() && answer.isNotBlank()) {
                jokesListViewModel.addNewJoke(category, question, answer)

                Toast.makeText(requireContext(), "Шутка добавлена!", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}