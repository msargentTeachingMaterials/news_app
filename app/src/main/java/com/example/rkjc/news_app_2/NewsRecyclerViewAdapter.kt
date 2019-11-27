package com.example.rkjc.news_app_2

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.rkjc.news_app_2.data.NewsItem
import com.example.rkjc.news_app_2.databinding.NewsItemBinding
import com.squareup.picasso.Picasso


class NewsRecyclerViewAdapter(val listener: NewsItemListener) : ListAdapter<NewsItem, NewsRecyclerViewAdapter.NewsViewHolder>(NewsItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, index: Int) {
         holder.bind(getItem(index), listener)
    }

    class NewsViewHolder private constructor(val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(newsItem: NewsItem, listener: NewsItemListener) {
            binding.item = newsItem
            binding.listener = listener


            if (newsItem.thumbURL != null && !newsItem.thumbURL!!.isEmpty())
                Picasso.get()
                        .load(newsItem.thumbURL)
                        .into(binding.img)

        }

        companion object{
            fun from(parent:ViewGroup): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewsItemBinding.inflate(layoutInflater, parent, false)
                return NewsViewHolder(binding)
            }
        }

    }
}

class NewsItemDiffCallback : DiffUtil.ItemCallback<NewsItem>(){
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }

}

class NewsItemListener(val clickListener: (newsItem:NewsItem) -> Unit){
    fun onClick(item: NewsItem) = clickListener(item)
}
