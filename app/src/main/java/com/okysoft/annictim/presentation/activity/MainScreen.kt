package com.okysoft.annictim.presentation.activity

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.okysoft.annictim.R
import com.okysoft.annictim.presentation.search.SearchActivity
import com.okysoft.annictim.presentation.setting.SettingActivity
import com.okysoft.annictim.presentation.works.FeedWorksScreen
import com.okysoft.annictim.presentation.works.MeWorksScreen

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: Int) {
    object Record : Screen("record", R.string.record, R.drawable.ic_star_24dp)
    object Feed : Screen("home", R.string.home, R.drawable.ic_home_24dp)
    object Program : Screen("program", R.string.program, R.drawable.ic_today_24dp)
    object Profile : Screen("profile", R.string.profile, R.drawable.ic_person_24dp)
}

@ExperimentalPagerApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val items = listOf(
        Screen.Record,
        Screen.Feed,
//        Screen.Program,
//        Screen.Profile,
    )

    val activity = LocalContext.current as? Activity
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    if (navBackStackEntry?.destination?.route == Screen.Feed.route) {
                        IconButton(onClick = {
                            activity?.let {
                                it.startActivity(SearchActivity.createIntent(it))
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Search,
                                contentDescription = "Search")
                        }
                    }
                    IconButton(onClick = {
                        activity?.let {
                            it.startActivity(SettingActivity.createIntent(it))
                        }
                    }) {
                        Icon(painter = painterResource(id = R.drawable.ic_settings_24dp),
                            contentDescription = "Setting")
                    }
                },
                elevation = 0.dp
            )
        },
        bottomBar = {
            BottomNavigation {
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = screen.route
                            )
                        },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                navController.graph.startDestinationRoute?.let {
                                    popUpTo(it) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController, startDestination = Screen.Record.route) {
            composable(route = Screen.Record.route) {
                MeWorksScreen()
            }
            composable(route = Screen.Feed.route) {
                FeedWorksScreen()
            }
            composable(route = Screen.Program.route) {
                MeWorksScreen()
            }
            composable(route = Screen.Profile.route) {
                MeWorksScreen()
            }
        }
    }
}