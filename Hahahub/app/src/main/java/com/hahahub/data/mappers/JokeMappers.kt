package com.hahahub.data.mappers

import com.hahahub.data.api.Joke as NetworkJoke
import com.hahahub.data.db.CachedJoke
import com.hahahub.data.db.SavedJoke
import com.hahahub.domain.models.Joke
import com.hahahub.domain.models.JokeSource

fun SavedJoke.toDomain(): Joke {
    return Joke(
        id = this.id,
        category = this.category,
        question = this.question,
        answer = this.answer,
        source = JokeSource.fromValue(this.source)
    )
}

fun CachedJoke.toDomain(): Joke {
    return Joke(
        id = this.id,
        category = this.category,
        question = this.question,
        answer = this.answer,
        source = JokeSource.fromValue(this.source)
    )
}

fun NetworkJoke.toDomain(): Joke {
    return Joke(
        id = this.id,
        category = this.category,
        question = this.setup,
        answer = this.delivery,
        source = JokeSource.NETWORK
    )
}

fun Joke.toCached(): CachedJoke {
    return CachedJoke(
        id = this.id,
        category = this.category,
        question = this.question,
        answer = this.answer,
        source = JokeSource.CACHED.value,
        timestamp = System.currentTimeMillis()
    )
}