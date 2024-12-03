package com.hahahub.data

enum class JokeSource(val value: String) {
    LOCAL("local"),
    NETWORK("network"),
    CACHED("cached");

    companion object {
        fun fromValue(value: String): JokeSource {
            return entries.find { it.value == value } ?: LOCAL
        }
    }
}
