package com.hahahub.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.hahahub.domain.models.Joke
import android.os.Bundle
import com.hahahub.domain.constants.Constants

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
            diffBundle.putString(Constants.CATEGORY_KEY, newItem.category)
        }
        if (oldItem.question != newItem.question) {
            diffBundle.putString(Constants.QUESTION_KEY, newItem.question)
        }
        if (oldItem.answer != newItem.answer) {
            diffBundle.putString(Constants.ANSWER_KEY, newItem.answer)
        }
        if (oldItem.source != newItem.source) {
            diffBundle.putString(Constants.SOURCE_KEY, newItem.source.value)
        }

        return if (diffBundle.size() == 0) null else diffBundle
    }
}