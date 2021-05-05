package com.example.imagepagelist.adapter

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagepagelist.R
import com.example.imagepagelist.databinding.ImageItemBinding
import com.example.imagepagelist.model.Photo
import javax.inject.Inject

class ImageListAdapter @Inject constructor() :
    PagingDataAdapter<Photo, ImageListAdapter.ImageItemViewHolder>(UserItemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageItemViewHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holderItem: ImageItemViewHolder, position: Int) {
        getItem(position)?.let { holderItem.bind(it) }
    }

    inner class ImageItemViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Photo) = with(binding) {

            var formID = item.farm
            var serverID = item.server
            var photoID = item.id.toString()+"_"+item.secret+".jpg"
            val url ="https://farm$formID.static.flickr.com/$serverID/$photoID"
            Log.d("UserItem",url)
            if (URLUtil.isValidUrl(url)) {
                Glide.with(binding.root.context).load(url).placeholder(R.drawable.ic_launcher_background).into(binding.imageView)
            }
        }
    }

    object UserItemComparator : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem == newItem
    }


}