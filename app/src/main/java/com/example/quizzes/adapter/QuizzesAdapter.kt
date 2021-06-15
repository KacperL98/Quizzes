package com.example.quizzes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.quizzes.database.ListItemEntity
import com.example.quizzes.databinding.ItemComicsBinding
import com.example.quizzes.model.Item
import com.example.quizzes.viewholder.QuizzesViewHolder

class QuizzesAdapter(private val listener: (Long) -> Unit) :
    ListAdapter<Item, QuizzesViewHolder>(DIFF_CALLBACK) {

    private val items = ArrayList<ListItemEntity>()

    fun setItems(items: ArrayList<ListItemEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizzesViewHolder {
        val binding =
            ItemComicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizzesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizzesViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {

            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem
        }
    }
}



