package com.android.sampleporject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.sampleporject.R
import com.android.sampleporject.model.Item
import com.bumptech.glide.Glide


class ImageListAdapter(val item: List<Item>) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.image_list_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(item[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return item.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: Item) {
            val textViewName = itemView.findViewById(R.id.title) as TextView
            val imageView = itemView.findViewById(R.id.imageView) as ImageView
            textViewName.text = user.title
            Glide.with(itemView.context).load(user.media.mediaLink).into(imageView)
                .onLoadStarted(ContextCompat.getDrawable(itemView.context, R.drawable.ic_image_loading))
        }
    }
}