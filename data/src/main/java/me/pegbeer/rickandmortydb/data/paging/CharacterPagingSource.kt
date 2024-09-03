package me.pegbeer.rickandmortydb.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.ApolloClient
import com.rickandmortydb.GetAllCharactersQuery
import me.pegbeer.rickandmortydb.core.model.Character
import java.lang.RuntimeException

class CharacterPagingSource (
    private val client:ApolloClient
):PagingSource<Int,Character>(){
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1

            val response = client.query(GetAllCharactersQuery(page)).execute()
            if(response.hasErrors())  throw RuntimeException (response.exception)

            if (response.data!!.characters?.results.isNullOrEmpty()) throw NullPointerException("No more resources to load")

            val characters = response.data!!.characters!!.results!!.map {
                Character(it!!.id,it.name,it.image,it.gender)
            }

            val info = response.data!!.characters?.info

            val prevKey = info?.prev ?: 1
            val nextKey = info?.next

            LoadResult.Page(
                data = characters,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }catch (ex:Exception){
            LoadResult.Error(ex)
        }
    }
}