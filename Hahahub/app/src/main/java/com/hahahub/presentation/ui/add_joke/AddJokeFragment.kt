package com.hahahub.presentation.ui.add_joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hahahub.databinding.FragmentAddJokeBinding

class AddJokeFragment : Fragment() {

    private var _binding: FragmentAddJokeBinding? = null
    private val binding get() = _binding!!

    private val addJokeViewModel: AddJokeViewModel by viewModels()

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
                addJokeViewModel.addNewJoke(category, question, answer)
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }

        addJokeViewModel.addJokeCompleted.observe(viewLifecycleOwner, Observer { isCompleted ->
            if (isCompleted) {
                Toast.makeText(requireContext(), "Шутка добавлена!", Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), "Ошибка при добавлении шутки!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}