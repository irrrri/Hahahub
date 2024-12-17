package com.hahahub.di.module

import android.content.Context
import androidx.room.Room
import com.hahahub.data.db.JokeDao
import com.hahahub.data.db.JokeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): JokeDatabase {
        return Room.databaseBuilder(context, JokeDatabase::class.java, "jokes.db").build()
    }

    @Provides
    fun provideJokeDao(database: JokeDatabase): JokeDao {
        return database.jokeDao()
    }
}
