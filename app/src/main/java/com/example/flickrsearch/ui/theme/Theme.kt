package com.example.flickrsearch.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FlickrSearchTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography(),
        content = content
    )
}

@Preview
@Composable
fun ThemePreview() {
    FlickrSearchTheme {
        Text("Hello, World!")
    }
}
