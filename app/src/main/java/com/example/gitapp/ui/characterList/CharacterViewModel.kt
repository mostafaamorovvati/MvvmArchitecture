package com.example.gitapp.ui.characterList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.apollographql.CharactersListQuery
import com.example.gitapp.data.repository.CharactersRepository
import com.example.gitapp.utils.ViewState
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: CharactersRepository) : ViewModel() {

    private val _charactersList by lazy { MutableLiveData<ViewState<Response<CharactersListQuery.Data>>>() }
    val charactersList: LiveData<ViewState<Response<CharactersListQuery.Data>>>
        get() = _charactersList


     fun runCharactersList() {
        viewModelScope.launch {
            _charactersList.postValue(ViewState.Loading())
            try {
                val response = repository.queryCharactersList()
                _charactersList.postValue(ViewState.Success(response))
            } catch (e: ApolloException) {
                Log.d("ApolloException", "Failure", e)
                _charactersList.postValue(ViewState.Error("Error fetching characters"))
            }
        }
    }

}