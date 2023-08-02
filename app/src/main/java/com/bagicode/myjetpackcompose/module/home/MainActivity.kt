package com.bagicode.myjetpackcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bagicode.myjetpackcompose.model.ProductResponse
import com.bagicode.myjetpackcompose.module.detail.DetailActivity
import com.bagicode.myjetpackcompose.module.home.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BottomMenu(viewModel)
        }
    }
}

@Composable
fun BottomMenu(viewModel: MainViewModel) {
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
            composable(Screen.Home.route) { HomeScreen(viewModel) }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}

@Composable
fun HomeScreen(viewModel : MainViewModel) {
    val items = viewModel.listProduct.value

    LaunchedEffect(true) {
        viewModel.getProduct()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(items) { item ->
//                Text(text = "This id ${item.title}")
                ItemCard(item = item, LocalContext.current)
            }
        }
    }
}

@Composable
fun ItemCard(item: ProductResponse.ProductItem, context: Context) {
    Card(
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                //Toast.makeText(context, "Item $item diklik!", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, DetailActivity::class.java)
                    .putExtra("title", item.title)
                    .putExtra("url", item.thumbnail)
                context.startActivity(intent)
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.title.orEmpty())
            Text(text = item.description.orEmpty())
            Text(text = item.thumbnail.orEmpty())
        }
    }
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

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewBottomMenu() {
//    BottomMenu()
//}