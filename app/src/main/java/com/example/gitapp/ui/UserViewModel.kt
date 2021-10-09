package com.example.gitapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitapp.data.remote.model.GithubUser
import com.example.gitapp.data.repository.UserRepository
import com.example.gitapp.utils.Resource
import kotlinx.coroutines.launch

class UserViewModel(private val repo: UserRepository) : ViewModel() {

    val mUsers = MutableLiveData<Resource<List<GithubUser>>>()

    fun runGetAllUsers() {
        viewModelScope.launch {
            mUsers.postValue(Resource.loading(null))
            try {
                repo.getAllUsers().let {
                    if (it.isSuccessful) {
                        val githubUsers = it.body()
                        if (githubUsers != null) {
                            mUsers.postValue(Resource.success(githubUsers))
                        } else
                            mUsers.postValue(Resource.error(it.message() ?: "error", null))
                    } else mUsers.postValue(Resource.error(it.message(), null))
                }
            } catch (e: Exception) {
                mUsers.postValue(Resource.error(e.message ?: "error", null))
            }
        }
    }


//    private val _loadingState = MutableLiveData<LoadingState>()
//    val loadingState: LiveData<LoadingState>
//        get() = _loadingState
//
//    private val _data = MutableLiveData<List<GithubUser>>()
//    val data: LiveData<List<GithubUser>>
//        get() = _data
//
//    init {
//        fetchData()
//    }
//
//    private fun fetchData() {
//        _loadingState.postValue(LoadingState.LOADING)
//        repo.getAllUsers().enqueue(this)
//    }
//
//    override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
//        _loadingState.postValue(LoadingState.error(t.message))
//    }
//
//    override fun onResponse(call: Call<List<GithubUser>>, response: Response<List<GithubUser>>) {
//        if (response.isSuccessful) {
//            _data.postValue(response.body())
//            _loadingState.postValue(LoadingState.LOADED)
//        } else {
//            _loadingState.postValue(LoadingState.error(response.errorBody().toString()))
//        }
//    }
}