package ru.captaindmitro.todoist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.captaindmitro.todoist.ui.details.DetailViewModel
import ru.captaindmitro.todoist.ui.details.DetailsScreen
import ru.captaindmitro.todoist.ui.home.HomeScreen
import ru.captaindmitro.todoist.ui.home.HomeViewModel
import ru.captaindmitro.todoist.ui.newtodo.NewTodoScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val detailViewModel: DetailViewModel = hiltViewModel()

    NavHost(
        navController = navHostController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen(homeViewModel, navHostController) }
        composable(
            "details/{todoItemId}",
            arguments = listOf(navArgument("todoItemId") { type = NavType.IntType })
        ) {
            DetailsScreen(detailViewModel, navHostController, it.arguments?.getInt("todoItemId"))
        }
        composable(
            "newtodo?editSelectedItem={editSelectedItem}",
            arguments = listOf(navArgument("editSelectedItem") { defaultValue = false })
        ) {
            NewTodoScreen(homeViewModel, detailViewModel, navHostController, it.arguments?.getBoolean("editSelectedItem"))
        }
    }
}