package me.pegbeer.rickandmortydb.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apollographql.apollo.ApolloClient
import com.rickandmortydb.GetAllCharactersQuery
import kotlinx.coroutines.test.runTest
import me.pegbeer.rickandmortydb.core.model.Character
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GraphQLInstrumentedTest {

    private lateinit var apolloClient: ApolloClient

    @Before
    fun setUp(){
        apolloClient = ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }

    @Test
    fun testGetAllCharacters() = runTest{
        val response = apolloClient.query(GetAllCharactersQuery(1)).execute()
        val characters = response.data?.characters?.results
        assert(characters != null)
        assert(characters!!.isNotEmpty())
        for (i in characters){
            println(i?.name)
        }
        println("SIZE: ${characters.size}")
    }

    @Test
    fun testMappingAllCharacters() = runTest {
        val response = apolloClient.query(GetAllCharactersQuery(1)).execute()

        val characters = response.data!!.characters!!.results

        val mappedCharacters = characters!!.map {
            it!!.let { char ->
                Character(char.id,char.name,char.image,char.gender)
            }
        }
        assert(mappedCharacters.isNotEmpty())
    }
}