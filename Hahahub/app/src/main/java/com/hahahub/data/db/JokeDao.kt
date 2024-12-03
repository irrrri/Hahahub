package com.hahahub.data.db

import androidx.room.*

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedJoke(joke: SavedJoke)

    @Query("SELECT * FROM saved_jokes ORDER BY source, id")
    suspend fun getSavedJokes(): List<SavedJoke>

    @Query("DELETE FROM saved_jokes")
    suspend fun clearSavedJokes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCachedJoke(joke: CachedJoke)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCachedJokes(jokes: List<CachedJoke>)

    @Query("SELECT * FROM cached_jokes WHERE timestamp > :validTime ORDER BY id")
    suspend fun getValidCachedJokes(validTime: Long): List<CachedJoke>

    @Query("SELECT * FROM cached_jokes WHERE timestamp > :validTime ORDER BY id, timestamp DESC")
    suspend fun getValidCachedJokesByTimestamp(validTime: Long): List<CachedJoke>

    @Query("DELETE FROM cached_jokes")
    suspend fun clearCachedJokes()

    @Query("SELECT * FROM saved_jokes WHERE id = :jokeId")
    suspend fun findSavedJokeById(jokeId: Int): SavedJoke?

    @Query("SELECT * FROM cached_jokes WHERE id = :jokeId")
    suspend fun findCachedJokeById(jokeId: Int): CachedJoke?
}
