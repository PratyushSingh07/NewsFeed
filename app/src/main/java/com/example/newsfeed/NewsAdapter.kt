package com.example.newsfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class NewsAdapter():RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<NewsData> = ArrayList();
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)//xml se view chng kiya
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleView.text=currentItem.title
        holder.creatorView.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.imgView)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateNews(updatedNews:ArrayList<NewsData>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val titleView:TextView=itemView.findViewById(R.id.title)
    val imgView:ImageView=itemView.findViewById(R.id.newsImage)
    val creatorView:TextView=itemView.findViewById(R.id.creator)
}