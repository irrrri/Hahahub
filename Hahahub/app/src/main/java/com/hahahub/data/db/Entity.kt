package com.hahahub.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "saved_jokes")
data class SavedJoke(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("question")
    val question: String,
    @SerializedName("answer")
    val answer: String,
    @SerializedName("source")
    val source: String
)

@Entity(tableName = "cached_jokes")
data class CachedJoke(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("question")
    val question: String,
    @SerializedName("answer")
    val answer: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("timestamp")
    val timestamp: Long
)
