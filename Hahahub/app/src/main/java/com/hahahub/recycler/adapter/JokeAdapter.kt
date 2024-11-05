package com.hahahub.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hahahub.databinding.JokeItemBinding
import com.hahahub.data.Joke
import com.hahahub.recycler.JokeViewHolder
import com.hahahub.recycler.util.JokeDiffUtilCallback
import android.os.Bundle
import com.hahahub.data.Constants

class JokeAdapter: RecyclerView.Adapter<JokeViewHolder>() {

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
        return JokeViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            val diffBundle = payloads[0] as Bundle
            holder.bind(diffBundle)
        } else {
            holder.bind(data[position])
        }
    }
}