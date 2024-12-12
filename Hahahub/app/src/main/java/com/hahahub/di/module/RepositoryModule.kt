package com.hahahub.di.module

import com.hahahub.data.api.JokeApiClient
import com.hahahub.data.db.JokeDao
import com.hahahub.data.repositories.JokeRepositoryImpl
import com.hahahub.domain.repositories.JokeRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideJokeRepository(
        jokeDao: JokeDao,
        jokeApiClient: JokeApiClient
    ): JokeRepository {
        return JokeRepositoryImpl(jokeDao, jokeApiClient)
    }
}
