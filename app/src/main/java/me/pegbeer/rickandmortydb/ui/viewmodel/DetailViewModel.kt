package me.pegbeer.rickandmortydb.ui.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.pegbeer.rickandmortydb.core.model.Character
import me.pegbeer.rickandmortydb.core.repository.Repository
import javax.inject.Inject

import me.pegbeer.rickandmortydb.core.Result

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    
    private val _character = MutableStateFlow<Result<Character>>(Result.loading())
    val character:StateFlow<Result<Character>> = _character.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Result.loading()
    )

    fun loadData(id:Int) {
        viewModelScope.launch {
            _character.value = repository.fetchCharacterDetails(id)
        }
    }
}