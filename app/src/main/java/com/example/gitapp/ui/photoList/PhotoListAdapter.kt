package com.example.gitapp.ui.photoList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.R
import com.example.gitapp.data.remote.model.Photo
import com.example.gitapp.utils.ImageLoader


class PhotoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mItems: ArrayList<Photo> = arrayListOf()

    companion object {
        const val PHOTO_ITEM = 0
        const val LOAD_MORE_ITEM = 1
        const val LOAD_MORE_TAG = "load_more_tag"
    }

    override fun getItemViewType(position: Int): Int {
        return if (mItems[position].loadMoreTag == LOAD_MORE_TAG) LOAD_MORE_ITEM else PHOTO_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PHOTO_ITEM -> PhotoListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.rv_item_layout, parent, false
                )
            )
            else -> LoadMoreViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.load_more_layout, parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            PHOTO_ITEM -> {
                val photoListHolder = (holder as PhotoListViewHolder)
                ImageLoader.loadImage(
                    mItems[position].download_url,
                    photoListHolder.imageView,
                    R.drawable.charlie_loader,
                    photoListHolder.itemView.context
                )
            }
            LOAD_MORE_ITEM -> {
                val loadMoreHolder = (holder as LoadMoreViewHolder)
            }
        }
    }

    override fun getItemCount() = mItems.size

    fun setData(
        list: ArrayList<Photo>,
        currentIndex: Int
    ) {
        if (currentIndex > 1) {
            mItems.addAll(list)
        } else {
            mItems = list
        }
        notifyDataSetChanged()
    }


    inner class PhotoListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    inner class LoadMoreViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress_circular)
    }
}