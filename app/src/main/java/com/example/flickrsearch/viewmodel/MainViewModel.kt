package com.example.flickrsearch.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrsearch.data.model.FlickrItem
import com.example.flickrsearch.data.network.ApiService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(FlowPreview::class)
class MainViewModel : ViewModel() {
    val query = MutableStateFlow("")
    private val _items = MutableStateFlow<List<FlickrItem>>(emptyList())
    val items: StateFlow<List<FlickrItem>> = _items.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val moshi = Moshi.Builder().build()

    private val apiService: ApiService = Retrofit.Builder()
        .baseUrl("https://api.flickr.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ApiService::class.java)

    init {
        viewModelScope.launch {
            query
                .debounce(500)
                .distinctUntilChanged()
                .filter { it.isNotBlank() }
                .collectLatest { searchTerm: String ->
                    _isLoading.value = true
                    try {
                        Log.d("MainViewModel", "Searching for: $searchTerm")
                        val tags = searchTerm.replace(",", " ")
                        val response = apiService.searchPhotos(tags)
                        Log.d("MainViewModel", "Received ${response.items.size} items")
                        _items.value = response.items
                    } catch (e: Exception) {
                        Log.e("MainViewModel", "Error fetching photos", e)
                        _items.value = emptyList()
                    } finally {
                        _isLoading.value = false
                    }
                }
        }
    }
}
