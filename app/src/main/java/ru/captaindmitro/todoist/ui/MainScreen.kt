package ru.captaindmitro.todoist.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.captaindmitro.todoist.R
import ru.captaindmitro.todoist.ui.navigation.NavGraph
import ru.captaindmitro.todoist.ui.newtodo.CategoriesRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    val navDestination by navController.currentBackStackEntryAsState()

    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text(text = stringResource(id = R.string.app_name)) },
//                navigationIcon = {
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
//                    }
//                },
//                actions = {
//                    IconButton(onClick = { navController.navigate("profile", NavOptions.Builder().setLaunchSingleTop(true).build()) }) {
//                        Icon(imageVector = Icons.Default.Person, contentDescription = "")
//                    }
//                },
//                colors = TopAppBarDefaults.smallTopAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
//                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
//                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
//                )
//            )
//        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.heightIn(max = 48.dp)
            ) {
                NavigationBarItem(
                    selected = navDestination?.destination?.route == "home",
                    onClick = { navController.navigate("home") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = ""
                        )
                    },
                )
                NavigationBarItem(
                    selected = navDestination?.destination?.route == "profile",
                    onClick = { navController.navigate("profile") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = ""
                        )
                    },
                )
            }
        },
        floatingActionButton = {
            navDestination?.destination?.route.let {
                if (it == "home") {
                    FloatingActionButton(
                        onClick = { navController.navigate("newtodo") }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        NavGraph(navHostController = navController, modifier = Modifier.padding(it))
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navHostController = rememberNavController()
    MainScreen(navController = navHostController)
}