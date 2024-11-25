package com.hahahub.data.api

data class JokeResponse(
    val error: Boolean,
    val amount: Int,
    val jokes: List<Joke>
)

data class Joke(
    val category: String,
    val type: String,
    val setup: String,
    val delivery: String,
    val flags: Flags,
    val safe: Boolean,
    val id: Int,
    val lang: String
)

data class Flags(
    val nsfw: Boolean = false,
    val religious: Boolean = false,
    val political: Boolean = false,
    val racist: Boolean = false,
    val sexist: Boolean = false,
    val explicit: Boolean = false
)