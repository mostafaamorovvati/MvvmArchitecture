package com.example.gitapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitapp.data.remote.model.Photo
import com.example.gitapp.data.repository.UserRepository
import com.example.gitapp.utils.NetworkHelper
import com.example.gitapp.utils.Resource
import kotlinx.coroutines.launch

class UserViewModel(
    private val mRepository: UserRepository,
    private val mNetworkHelper: NetworkHelper
) : ViewModel() {

    val mUsers = MutableLiveData<Resource<List<Photo>>>()

    fun runGetAllUsers() {
        viewModelScope.launch {
            mUsers.postValue(Resource.loading(null))
            try {
                if (mNetworkHelper.isNetworkConnected()) {
                    mRepository.getAllUsers().let {
                        if (it.isSuccessful) {
                            val githubUsers = it.body()
                            if (githubUsers != null) {
                                mUsers.postValue(Resource.success(githubUsers))
                            } else
                                mUsers.postValue(Resource.error(it.message() ?: "error", null))
                        } else
                            mUsers.postValue(Resource.error(it.message(), null))
                    }
                } else
                    mUsers.postValue(Resource.error("No internet", null))
            } catch (e: Exception) {
                mUsers.postValue(Resource.error(e.message ?: "error", null))
            }
        }
    }
}