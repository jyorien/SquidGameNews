package com.example.squidgamenews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.squidgamenews.data.News

class NewsAdapter : ListAdapter<News, NewsVH>(NewsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout, parent, false)
        return NewsVH(view)
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        holder.bind(getItem(position))
    }
}
class NewsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.news_title)
    private val url: TextView = itemView.findViewById(R.id.news_url)
    private val source: TextView = itemView.findViewById(R.id.news_source)
    fun bind(news: News) {
        val titleFormatted = news.title.trim()
        title.text = titleFormatted
        url.text = news.url
        source.text = news.source
    }

}
class NewsComparator: DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return  oldItem.title == newItem.title
    }

}