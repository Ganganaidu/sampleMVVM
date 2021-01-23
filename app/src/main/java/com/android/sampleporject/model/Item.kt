package com.android.sampleporject.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("title")
    val title: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("media")
    val media: Media? = null,
    @SerializedName("date_taken")
    val dateTaken: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("published")
    val published: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("author_id")
    val authorId: String? = null,
    @SerializedName("tags")
    val tags: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Media::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(link)
        parcel.writeParcelable(media, flags)
        parcel.writeString(dateTaken)
        parcel.writeString(description)
        parcel.writeString(published)
        parcel.writeString(author)
        parcel.writeString(authorId)
        parcel.writeString(tags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}