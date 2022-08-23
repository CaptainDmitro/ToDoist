package ru.captaindmitro.todoist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.captaindmitro.todoist.ui.newtodo.DetailViewModel
import ru.captaindmitro.todoist.ui.home.HomeScreen
import ru.captaindmitro.todoist.ui.home.HomeViewModel
import ru.captaindmitro.todoist.ui.newtodo.EditTodoScreen
import ru.captaindmitro.todoist.ui.profile.ProfileScreen

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
            "newtodo?todoItemId={todoItemId}",
            arguments = listOf(
                navArgument("todoItemId") { defaultValue = 0 }
            )
        ) {
            EditTodoScreen(
                homeViewModel = homeViewModel,
                detailViewModel = detailViewModel,
                navController = navHostController,
                todoItemId = it.arguments?.getInt("todoItemId")
            )
        }
        composable("profile") { ProfileScreen() }
    }
}