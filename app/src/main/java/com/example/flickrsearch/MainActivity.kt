package com.example.flickrsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import com.example.flickrsearch.ui.screens.DetailScreen
import com.example.flickrsearch.ui.screens.MainScreen
import com.example.flickrsearch.ui.theme.FlickrSearchTheme
import androidx.navigation.compose.*
import com.example.flickrsearch.data.model.FlickrItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrSearchTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(onItemClick = { item ->
                            navController.currentBackStackEntry?.savedStateHandle?.set("item", item)
                            navController.navigate("detail")
                        })
                    }
                    composable("detail") {
                        // Retrieve the item from savedStateHandle
                        val item = navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.get<FlickrItem>("item")
                        if (item != null) {
                            DetailScreen(item = item, onBackClick = {
                                navController.popBackStack()
                            })
                        } else {
                            Text("No item data available.")
                        }
                    }
                }
            }
        }
    }
}
