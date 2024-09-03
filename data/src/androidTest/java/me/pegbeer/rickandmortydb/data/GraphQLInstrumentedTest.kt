package me.pegbeer.rickandmortydb.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apollographql.apollo.ApolloClient
import com.rickandmortydb.GetAllCharactersQuery
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GraphQLInstrumentedTest {

    @Test
    fun testGetAllCharacters() = runTest{
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()

        val response = apolloClient.query(GetAllCharactersQuery()).execute()
        val characters = response.data?.characters?.results
        assert(characters != null)
        assert(characters!!.isNotEmpty())
        for (i in characters){
            println(i?.name)
        }
        println("SIZE: ${characters.size}")
    }
}