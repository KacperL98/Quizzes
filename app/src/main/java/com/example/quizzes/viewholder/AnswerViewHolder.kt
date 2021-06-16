package com.example.quizzes.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.quizzes.R
import com.example.quizzes.databinding.ItemAnswerBinding
import com.example.quizzes.model.Answer
import timber.log.Timber

class AnswerViewHolder(private val binding: ItemAnswerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(answer: Answer, onClickAnswer: (Answer) -> Unit) {
        with(binding) {
            btnAnswer.text = answer.text
            btnAnswer.setOnClickListener { onClickAnswer.invoke(answer) }
        }
    }
}