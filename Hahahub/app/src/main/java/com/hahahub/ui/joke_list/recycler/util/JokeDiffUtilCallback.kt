package com.hahahub.ui.joke_list.recycler.util

import androidx.recyclerview.widget.DiffUtil
import com.hahahub.data.Joke
import android.os.Bundle

class JokeDiffUtilCallback(
    private val oldList: List<Joke>,
    private val newList: List<Joke>
): DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        val diffBundle = Bundle()

        if (oldItem.category != newItem.category) {
            diffBundle.putString("category", newItem.category)
        }
        if (oldItem.question != newItem.question) {
            diffBundle.putString("question", newItem.question)
        }
        if (oldItem.answer != newItem.answer) {
            diffBundle.putString("answer", newItem.answer)
        }

        return if (diffBundle.size() == 0) null else diffBundle
    }
}