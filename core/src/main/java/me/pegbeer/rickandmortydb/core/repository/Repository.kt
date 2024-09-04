package me.pegbeer.rickandmortydb.core.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.pegbeer.rickandmortydb.core.Result
import me.pegbeer.rickandmortydb.core.model.Character

interface Repository {
    fun fetchCharacterList():Flow<PagingData<Character>>

    fun fetchCharacterDetails(id:Int):Flow<Result<Character>>
}