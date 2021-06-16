package com.example.quizzes.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.quizzes.R
import com.example.quizzes.databinding.ItemQuizBinding
import com.example.quizzes.model.roommodel.RoomModelData

class QuizViewHolder(private val binding: ItemQuizBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RoomModelData, listener: (Long) -> Unit) {
        with(binding) {
            txtTitleQuiz.text = item.title
            val url =
                item.mainPhoto.url
                    .replace(
                        "https://filerepo.grupawp.pl/",
                        "http://i.wpimg.pl/" +
                                "${item.mainPhoto.width}x${item.mainPhoto.height}/filerepo.grupawp.pl/"
                    )
            Glide.with(root.context)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.quiz_list)
                .into(imgTitleQuiz)
            root.setOnClickListener { listener.invoke(item.id) }
        }
    }
}