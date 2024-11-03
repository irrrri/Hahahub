package com.hahahub.ui.joke_list.recycler.adapter

import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hahahub.databinding.JokeItemBinding
import com.hahahub.data.Joke
import com.hahahub.ui.joke_list.recycler.JokeViewHolder
import com.hahahub.ui.joke_list.recycler.util.JokeDiffUtilCallback
import android.os.Bundle
import android.widget.Toast

class JokeAdapter(
    private val clickListener: (Int) -> Unit
): RecyclerView.Adapter<JokeViewHolder>() {

    private var data = emptyList<Joke>()

    fun setNewData(newData: List<Joke>) {
        val diffUtilCallback = JokeDiffUtilCallback(data, newData)
        val calculateDiff = DiffUtil.calculateDiff(diffUtilCallback)
        data = newData
        calculateDiff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JokeItemBinding.inflate(inflater, parent, false)
        return JokeViewHolder(binding).apply {
            binding.root.setOnClickListener {
                handleJokeClick(adapterPosition)
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            val diffBundle = payloads[0] as Bundle
            diffBundle.keySet().forEach { key ->
                when (key) {
                    "category" -> holder.binding.jokeCategory.text = diffBundle.getString("category")
                    "question" -> holder.binding.jokeQuestion.text = diffBundle.getString("question")
                    "answer" -> holder.binding.jokeAnswer.text = diffBundle.getString("answer")
                }
            }
        } else {
            holder.bind(data[position])
        }
    }

    private fun handleJokeClick(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val item = data[position]
            clickListener(position)
        }
    }
}