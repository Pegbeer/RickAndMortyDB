package me.pegbeer.rickandmortydb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.cachedIn
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import me.pegbeer.rickandmortydb.core.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    val characters = repository.fetchCharacterList()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)
}