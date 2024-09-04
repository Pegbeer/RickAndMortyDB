package me.pegbeer.rickandmortydb.data.remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import com.rickandmortydb.GetCharacterDetailsQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.pegbeer.rickandmortydb.core.Result
import me.pegbeer.rickandmortydb.core.model.Character
import me.pegbeer.rickandmortydb.core.repository.Repository
import me.pegbeer.rickandmortydb.data.paging.CharacterPagingSource

class RepositoryImpl(
    private val apolloClient: ApolloClient
) : Repository {
    override fun fetchCharacterList(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = { CharacterPagingSource(apolloClient) }
        ).flow
    }

    override fun fetchCharacterDetails(id:Int): Flow<Result<Character>> = flow{
        println("ID OF THE CHARACTER : $id")
        val response = apolloClient.query(GetCharacterDetailsQuery(id.toString())).execute()
        if(response.hasErrors()){
            emit(Result.error(-1))
        }

        response.data?.let {
            val result = it.character
            val character = Character(
                result.id,
                result.name,
                result.image,
                result.gender,
                result.status,
                result.created,
                result.origin.name,
                result.origin.dimension
            )
            emit(Result.success(character))
        }
    }
}