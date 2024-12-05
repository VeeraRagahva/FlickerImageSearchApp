package com.example.flickrsearch.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flickrsearch.data.model.FlickrItem
import com.example.flickrsearch.data.model.Media
import com.example.flickrsearch.ui.components.ImageGrid
import com.example.flickrsearch.ui.components.SearchBar
import com.example.flickrsearch.viewmodel.MainViewModel

@Composable
fun MainScreen(
    onItemClick: (FlickrItem) -> Unit,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val query by viewModel.query.collectAsState()
    val items by viewModel.items.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column {
        SearchBar(
            query = query,
            onValueChange = { newValue ->
                viewModel.query.value = newValue
            }
        )
        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        ImageGrid(items = items, onItemClick = onItemClick)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val sampleItems = List(10) {
        FlickrItem(
            title = "Sample Image",
            link = "",
            media = Media(m = "https://via.placeholder.com/150"),
            dateTaken = "",
            description = "",
            published = "",
            author = "",
            authorId = "",
            tags = ""
        )
    }
    var query by remember { mutableStateOf("porcupine") }
    Column {
        SearchBar(
            query = query,
            onValueChange = { newValue -> query = newValue }
        )
        ImageGrid(items = sampleItems, onItemClick = {})
    }
}
