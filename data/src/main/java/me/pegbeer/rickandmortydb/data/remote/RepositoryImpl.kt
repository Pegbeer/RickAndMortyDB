package me.pegbeer.rickandmortydb.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import kotlinx.coroutines.flow.Flow
import me.pegbeer.rickandmortydb.core.model.Character
import me.pegbeer.rickandmortydb.core.repository.Repository
import me.pegbeer.rickandmortydb.data.paging.CharacterPagingSource

class RepositoryImpl(
    private val apolloClient: ApolloClient
) : Repository {
    override suspend fun fetchCharacterList(page: Int): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = { CharacterPagingSource(apolloClient) }
        ).flow
    }
}