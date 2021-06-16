package com.example.quizzes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.quizzes.databinding.ItemQuizBinding
import com.example.quizzes.model.ViewStatus
import com.example.quizzes.model.roommodel.RoomModelData
import com.example.quizzes.viewholder.QuizViewHolder

class QuizAdapter(private val onClickQuiz: (Long) -> Unit) :
    ListAdapter<RoomModelData, QuizViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding =
            ItemQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.bind(getItem(position), onClickQuiz)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RoomModelData>() {

            override fun areItemsTheSame(oldItem: RoomModelData, newItem: RoomModelData): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: RoomModelData, newItem: RoomModelData): Boolean =
                oldItem == newItem
        }
    }
}



