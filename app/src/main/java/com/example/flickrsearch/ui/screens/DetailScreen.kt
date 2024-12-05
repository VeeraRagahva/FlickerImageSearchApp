package com.example.flickrsearch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.flickrsearch.data.model.FlickrItem
import com.example.flickrsearch.data.model.Media
import com.example.flickrsearch.utils.DateUtils
import com.example.flickrsearch.utils.HtmlUtils

@Composable
fun DetailScreen(item: FlickrItem, onBackClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Image(
            painter = rememberAsyncImagePainter(item.media.m),
            contentDescription = item.title,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Author: ${item.author}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Published: ${DateUtils.formatDate(item.published)}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = HtmlUtils.fromHtml(item.description).toString(),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        val dimensions = extractDimensions(item.description)
        Text(text = "Dimensions: $dimensions", style = MaterialTheme.typography.bodyMedium)
    }
}

fun extractDimensions(description: String): String {
    val regex = """width="(\d+)" height="(\d+)"""".toRegex()
    val match = regex.find(description)
    return if (match != null) {
        val (width, height) = match.destructured
        "${width}px x ${height}px"
    } else {
        "Unknown"
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val sampleItem = FlickrItem(
        title = "Sample Image",
        link = "",
        media = Media(m = "https://via.placeholder.com/600"),
        dateTaken = "",
        description = "<p><img src=\"https://via.placeholder.com/600\" width=\"600\" height=\"400\" /></p>",
        published = "2024-12-03T07:07:17Z",
        author = "nobody@flickr.com (\"Sample Author\")",
        authorId = "",
        tags = ""
    )
    DetailScreen(item = sampleItem, onBackClick = {})
}
