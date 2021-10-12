package com.example.gitapp.ui.photoList

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitapp.data.remote.model.Photo
import com.example.gitapp.databinding.ActivityShowPhotoBinding
import com.example.gitapp.utils.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowPhotoActivity : AppCompatActivity() {

    private val mShowPhotoViewModel: ShowPhotoViewModel by viewModel()
    private val mPhotoListAdapter: PhotoListAdapter by inject()
    private lateinit var mBinding: ActivityShowPhotoBinding
    private lateinit var scrollListener: EndlessRecyclerOnScrollListenerLinear
    private var isFirstLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityShowPhotoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupPhotoList()
        setupObserver()

    }


    private fun setupObserver() {
        mShowPhotoViewModel.mUsers.observe(this, Observer {
            it?.let { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        deleteProgressOfList()
                        scrollListener.newDataReceived()
                        mBinding.progressCircular.gone()
                        mBinding.rvPhotoList.visible()
                        it.data?.let { it1 ->
                            mPhotoListAdapter.setData(
                                it1,
                                mShowPhotoViewModel.getCurrentPage()
                            )
                        }

                        isFirstLoad = false
                    }
                    Status.ERROR -> {
                        mBinding.progressCircular.gone()
                        mBinding.rvPhotoList.gone()
                        this.showToast(it.message.toString())
                    }

                    Status.LOADING -> {
                        if (isFirstLoad) {
                            mBinding.progressCircular.visible()
                            mBinding.rvPhotoList.gone()
                        }
                    }
                }
            }
        })
    }


    private fun setupPhotoList() {
        mBinding.rvPhotoList.apply {
            val mLinearLayoutManager = LinearLayoutManager(context)
            mLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = mLinearLayoutManager
            adapter = mPhotoListAdapter
            scrollListener =
                object : EndlessRecyclerOnScrollListenerLinear(mLinearLayoutManager) {
                    override fun onScrollStateChanged(newState: Int) {

                    }

                    override fun onLoadMore(current_page: Int) {
//                        if (response?.totalCount != null && (response!!.totalCount!!) <= mAdapter.list.size) {
//                            return
//                        }

                        addProgressToList()

                        mShowPhotoViewModel.setPage(mShowPhotoViewModel.getCurrentPage() + 1)
                        mShowPhotoViewModel.runGetAllPhotos()


                    }
                }
            addOnScrollListener(scrollListener)
        }
    }


    private fun addProgressToList() {
        val photo = Photo(loadMoreTag = PhotoListAdapter.LOAD_MORE_TAG)
        mPhotoListAdapter.mItems.add(photo)
        mPhotoListAdapter.notifyDataSetChanged()
    }


    private fun deleteProgressOfList() {
        if (mPhotoListAdapter.mItems.size > 0 && mPhotoListAdapter.mItems[mPhotoListAdapter.mItems.size - 1].loadMoreTag == PhotoListAdapter.LOAD_MORE_TAG) {
            mPhotoListAdapter.mItems.removeAt(mPhotoListAdapter.mItems.size - 1)
            mPhotoListAdapter.notifyDataSetChanged()
        }
    }
}
