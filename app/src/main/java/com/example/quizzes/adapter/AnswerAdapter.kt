package com.example.quizzes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.quizzes.databinding.ItemAnswerBinding
import com.example.quizzes.model.Answer
import com.example.quizzes.viewholder.AnswerViewHolder

class AnswerAdapter(private val listener: (Answer) -> Unit) :
    ListAdapter<Answer, AnswerViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val binding =
            ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnswerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Answer>() {

            override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean =
                oldItem == newItem
        }
    }
}