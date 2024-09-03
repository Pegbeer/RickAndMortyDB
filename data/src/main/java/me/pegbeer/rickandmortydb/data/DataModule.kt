package me.pegbeer.rickandmortydb.data

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.pegbeer.rickandmortydb.core.repository.Repository
import me.pegbeer.rickandmortydb.data.remote.RepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRepository(apolloClient: ApolloClient):Repository{
        return RepositoryImpl(apolloClient)
    }

    @Provides
    fun provideApolloClient():ApolloClient{
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }
}