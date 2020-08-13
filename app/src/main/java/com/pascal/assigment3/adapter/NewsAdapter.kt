package com.pascal.assigment3.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pascal.assigment3.R
import com.pascal.assigment3.detail.DetailActivity
import com.pascal.assigment3.model.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(var data: ArrayList<News>?) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: NewsAdapter.NewsHolder, position: Int) {
        holder.title.text = data?.get(position)?.title
        holder.desc.text = data?.get(position)?.description

        Glide.with(holder.itemView.context)
            .load(data?.get(position)?.urlToImage)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("title", data?.get(position)?.title)
            intent.putExtra("desc", data?.get(position)?.description)
            intent.putExtra("image", data?.get(position)?.urlToImage)
            intent.putExtra("author", data?.get(position)?.author)
            holder.itemView.context.startActivity(intent)
        }
    }

    inner class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.item_image
        val title = itemView.item_title
        val desc = itemView.item_deskripsi
    }
}