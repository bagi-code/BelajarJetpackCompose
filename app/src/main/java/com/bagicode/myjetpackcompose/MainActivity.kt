package com.bagicode.myjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BottomMenu()
        }
    }
}

@Composable
fun BottomMenu() {
    val items = listOf(
        Screen.Home,
        Screen.Profile,
        Screen.Settings
    )

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(id = screen.icon),
                                contentDescription = screen.label
                            )
                        },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { //innerPadding ->
        NavHost(navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}

@Composable
fun HomeScreen() {
    Text(text = "This home page")
}

@Composable
fun ProfileScreen() {
    Text(text = "This profile page")
}

@Composable
fun SettingsScreen() {
    Text(text = "This setting page")
}

sealed class Screen(val route: String, val label: String, val icon: Int) {
    object Home : Screen("home", "Home", R.drawable.ic_home)
    object Profile : Screen("profile", "Profile", R.drawable.ic_profile)
    object Settings : Screen("settings", "Settings", R.drawable.ic_settings)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBottomMenu() {
    BottomMenu()
}