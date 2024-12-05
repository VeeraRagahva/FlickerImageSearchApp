package com.example.flickrsearch.data.network

import com.example.flickrsearch.data.model.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun searchPhotos(@Query("tags") tags: String): FlickrResponse
}
