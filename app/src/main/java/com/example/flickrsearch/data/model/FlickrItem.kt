package com.example.flickrsearch.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class FlickrItem(
    val title: String,
    val link: String,
    val media: Media,
    @Json(name = "date_taken") val dateTaken: String,
    val description: String,
    val published: String,
    val author: String,
    @Json(name = "author_id") val authorId: String,
    val tags: String
) : Serializable

@JsonClass(generateAdapter = true)
data class Media(
    val m: String
) : Serializable
