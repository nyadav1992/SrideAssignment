package com.example.srideassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.srideassignment.data.Article
import com.example.srideassignment.databinding.RowNewsItemBinding

class NewsAdapter : ListAdapter<Article, NewsAdapter.RepoViewHolder>(DiffUtil()) {

    class RepoViewHolder(val rowNewsItemBinding: RowNewsItemBinding):
        RecyclerView.ViewHolder(rowNewsItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflate =
            RowNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)

        holder.rowNewsItemBinding.newsData = item
    }

    class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem == newItem
        }
    }
}