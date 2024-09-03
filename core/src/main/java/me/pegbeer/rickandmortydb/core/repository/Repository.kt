package me.pegbeer.rickandmortydb.core.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.pegbeer.rickandmortydb.core.model.Character

interface Repository {
    suspend fun fetchCharacterList(page:Int):Flow<PagingData<Character>>
}