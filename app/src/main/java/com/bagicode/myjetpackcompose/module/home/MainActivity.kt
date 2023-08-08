package com.bagicode.myjetpackcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomMenu(viewModel: MainViewModel) {
    val items = listOf(
        Screen.Home,
        Screen.Profile,
        Screen.Settings
    )

    val selectedItem by remember { mutableStateOf(0) }
    val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(painterResource(id = item.icon), contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = selectedItem == index,
                            onClick = { //selectedItem = index
                                 navController.navigate(item.route) {
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
            },
            content = { paddingValues ->
                Surface {
                    Column(modifier = Modifier.padding(paddingValues)) {
                        NavHost(navController, startDestination = Screen.Home.route) {
                            composable(Screen.Home.route) { HomeScreen(viewModel) }
                            composable(Screen.Profile.route) { ProfileScreen() }
                            composable(Screen.Settings.route) { SettingsScreen() }
                        }
                    }
                }


            }
        )


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
                ItemCard(item = item, LocalContext.current)
            }
        }
    }
}

@Composable
fun ItemCard(item: ProductResponse.ProductItem, context: Context) {
    Card(
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