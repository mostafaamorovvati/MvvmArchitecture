package com.example.gitapp.ui.characterList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gitapp.databinding.ActivityCharacterBinding
import com.example.gitapp.utils.ViewState
import com.example.gitapp.utils.gone
import com.example.gitapp.utils.visible
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCharacterBinding
    private val mCharacterViewModel: CharacterViewModel by viewModel()
    private val mCharacterAdapter: CharacterAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.charactersRv.adapter = mCharacterAdapter
        mCharacterViewModel.runCharactersList()
        observeLiveData()
    }

    private fun observeLiveData() {
        mCharacterViewModel.charactersList.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.charactersRv.gone()
                    binding.charactersFetchProgress.visible()
                }
                is ViewState.Success -> {
                    if (response.value?.data?.characters?.results?.size == 0) {
                        mCharacterAdapter.submitList(emptyList())
                        binding.charactersFetchProgress.gone()
                        binding.charactersRv.visible()
                        binding.charactersEmptyText.visible()
                    } else {
                        binding.charactersRv.visible()
                        binding.charactersEmptyText.gone()
                    }
                    val results = response.value?.data?.characters?.results
                    mCharacterAdapter.submitList(results)
                    binding.charactersFetchProgress.gone()
                }
                is ViewState.Error -> {
                    mCharacterAdapter.submitList(emptyList())
                    binding.charactersFetchProgress.gone()
                    binding.charactersRv.gone()
                    binding.charactersEmptyText.visible()
                }
            }
        }
    }

}
