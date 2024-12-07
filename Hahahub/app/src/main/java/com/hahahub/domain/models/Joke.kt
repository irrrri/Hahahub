package com.hahahub.domain.models

import android.os.Parcel
import android.os.Parcelable

data class Joke(
    val id: Int,
    val category: String,
    val question: String,
    val answer: String,
    val source: JokeSource
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        JokeSource.fromValue(parcel.readString() ?: "")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(category)
        parcel.writeString(question)
        parcel.writeString(answer)
        parcel.writeString(source.value)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Joke> {
        override fun createFromParcel(parcel: Parcel): Joke = Joke(parcel)
        override fun newArray(size: Int): Array<Joke?> = arrayOfNulls(size)
    }
}