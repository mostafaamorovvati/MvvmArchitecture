package com.example.gitapp.ui.photoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.data.remote.model.Photo
import com.example.gitapp.databinding.LoadMoreLayoutBinding
import com.example.gitapp.databinding.RvItemLayoutBinding
import com.example.gitapp.utils.ImageLoader



class PhotoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     var mItems: ArrayList<Photo> = arrayListOf()

    companion object{
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
                RvItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> LoadMoreViewHolder(
                LoadMoreLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
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
                    photoListHolder.mBinding.imageView,
                    0,
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





    inner class PhotoListViewHolder(val mBinding: RvItemLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root)

    inner class LoadMoreViewHolder(val mBinding: LoadMoreLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root)
}