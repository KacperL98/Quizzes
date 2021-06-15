package com.example.quizzes.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.quizzes.R
import com.example.quizzes.databinding.ItemComicsBinding
import com.example.quizzes.model.Item
import com.squareup.picasso.Picasso
import timber.log.Timber

class QuizzesViewHolder(private val binding: ItemComicsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item, listener: (Long) -> Unit) {

        with(binding) {
            titleComics.text = item.title
            val url =
                item.mainPhoto.url.replace("https://filerepo.grupawp.pl/", "http://i.wpimg.pl/${item.mainPhoto.width}x${item.mainPhoto.height}/filerepo.grupawp.pl/")

            Glide.with(itemView)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_launcher_background)
                .into(img)
            root.setOnClickListener { listener.invoke(item.id) }
            Timber.e("XD")

        }
    }
}