package com.carlosprieto.randomuser.view.utils

import androidx.compose.runtime.Composable
import com.carlosprieto.randomuser.view.detail.DetailsView
import com.carlosprieto.randomuser.view.home.HomeView
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRoutes.HomeView) {
        composable(NavigationRoutes.HomeView) {
            HomeView(navController)
        }
        composable(
            "${NavigationRoutes.DetailsView}/{${NavigationArgs.FirstName}}/{${NavigationArgs.LastName}}/{${NavigationArgs.Email}}/{${NavigationArgs.Gender}}/{${NavigationArgs.RegisterDate}}/{${NavigationArgs.Phone}}/{${NavigationArgs.Image}}",
            arguments = listOf(
                navArgument(NavigationArgs.Gender) { type = NavType.StringType },
                navArgument(NavigationArgs.FirstName) { type = NavType.StringType },
                navArgument(NavigationArgs.LastName) { type = NavType.StringType },
                navArgument(NavigationArgs.Email) { type = NavType.StringType },
                navArgument(NavigationArgs.RegisterDate) { type = NavType.StringType },
                navArgument(NavigationArgs.Phone) { type = NavType.StringType },
                navArgument(NavigationArgs.Image) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            DetailsView(
                navController = navController,
                firstName = backStackEntry.arguments?.getString(NavigationArgs.FirstName) ?: "",
                lastName = backStackEntry.arguments?.getString(NavigationArgs.LastName) ?: "",
                email = backStackEntry.arguments?.getString(NavigationArgs.Email) ?: "",
                gender = backStackEntry.arguments?.getString(NavigationArgs.Gender) ?: "",
                registerDate = backStackEntry.arguments?.getString(NavigationArgs.RegisterDate) ?: "",
                phone = backStackEntry.arguments?.getString(NavigationArgs.Phone) ?: "",
                thumbnail = backStackEntry.arguments?.getString(NavigationArgs.Image) ?: ""
            )
        }
    }
}
