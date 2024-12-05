package com.example.flickrsearch.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.flickrsearch.data.model.FlickrItem
import com.example.flickrsearch.data.model.Media

@Composable
fun ImageGrid(
    items: List<FlickrItem>,
    onItemClick: (FlickrItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { item ->
            Image(
                painter = rememberAsyncImagePainter(item.media.m),
                contentDescription = item.title,
                modifier = Modifier
                    .padding(4.dp)
                    .aspectRatio(1f)
                    .clickable { onItemClick(item) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageGridPreview() {
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
    ImageGrid(items = sampleItems, onItemClick = {})
}
