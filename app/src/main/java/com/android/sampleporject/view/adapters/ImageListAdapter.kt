package com.android.sampleporject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sampleporject.R
import com.android.sampleporject.model.Item
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

interface OnItemClickListener {
    fun onItemClicked(item: Item)
}

class ImageListAdapter(
    private val item: List<Item>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.image_list_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(item[position], listener)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return item.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName = itemView.findViewById(R.id.title) as TextView
        private val imageView = itemView.findViewById(R.id.imageView) as ImageView

        fun bindItems(item: Item, listener: OnItemClickListener) {
            textViewName.text = item.title

            val requestOption = RequestOptions()
                .placeholder(R.drawable.ic_image_loading)

            Glide.with(itemView.context)
                .load(item.media?.mediaLink)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(requestOption)
                .into(imageView)

            imageView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }
}