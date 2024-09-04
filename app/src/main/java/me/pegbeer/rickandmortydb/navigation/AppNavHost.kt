package me.pegbeer.rickandmortydb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.pegbeer.rickandmortydb.ui.screen.DetailScreen
import me.pegbeer.rickandmortydb.ui.screen.HomeScreen
import me.pegbeer.rickandmortydb.ui.viewmodel.HomeViewModel

@Composable
fun AppNavHost(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            HomeScreen(navController)
        }
        composable("detail/{id}", arguments = listOf(navArgument("id"){ type = NavType.IntType })){ backStackEntry ->
            DetailScreen(id = backStackEntry.arguments!!.getInt("id"), navController = navController)
        }
    }
}