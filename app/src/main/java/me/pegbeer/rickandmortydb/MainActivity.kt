package me.pegbeer.rickandmortydb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.pegbeer.rickandmortydb.core.ui.theme.RickAndMortyDBTheme
import me.pegbeer.rickandmortydb.navigation.AppNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyDBTheme {
                val navHostController = rememberNavController()
                AppNavHost(navController = navHostController)
            }
        }
    }
}