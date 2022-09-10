package ru.mts.data.news.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ru.mts.data.news.db.NewsEntity


class NewsDto {
    @Parcelize
    class Request : Parcelable

    @Parcelize
    data class Response(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("full_description")
        val full_description: String,
        @SerializedName("pubDate")
        val pubDate: String
        ): Parcelable
}

internal fun NewsDto.Response.toDomain(): NewsEntity {
    return NewsEntity(
        id,
        title,
        description,
        link,
        full_description,
        pubDate
    )
}