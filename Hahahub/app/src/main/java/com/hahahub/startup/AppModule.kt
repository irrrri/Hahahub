package com.hahahub.startup

import android.app.Application
import com.hahahub.data.db.JokeDao
import com.hahahub.data.db.JokeDatabase
import com.hahahub.data.api.JokeApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJokeDao(app: Application): JokeDao {
        return JokeDatabase.getDatabase(app).jokeDao()
    }

    @Provides
    @Singleton
    fun provideJokeApiClient(): JokeApiClient {
        return JokeApiClient()
    }
}
