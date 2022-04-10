package com.example.costhistoryv2.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.costhistoryv2.domain.units.NavigationUnit
import com.example.costhistoryv2.presentation.screens.cost_history.CostHistoryScreen
import com.example.costhistoryv2.ui.theme.BoldBlue
import com.example.costhistoryv2.ui.theme.BoldGreen
import com.example.costhistoryv2.ui.theme.DarkBlue
import com.example.costhistoryv2.ui.theme.Sizes
import kotlin.math.roundToInt

class Navigator {

    var currentNavigationItem:NavigationItem = NavigationItem.CostHistory
    var floatingButtonAction: () -> Unit = {}

    @Composable
    fun Content() {
        val navController = rememberNavController()
        val bottomNavHeight = Sizes.bottomNavHeight
        val bottomBarHeightPx = with(LocalDensity.current) { bottomNavHeight.roundToPx().toFloat() }
        val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                    val delta = available.y
                    val newOffset = bottomBarOffsetHeightPx.value + delta
                    bottomBarOffsetHeightPx.value = newOffset.coerceIn(-bottomBarHeightPx, 0f)

                    return Offset.Zero
                }
            }
        }

        val floatingButtonShape = RoundedCornerShape(50.dp)

        Scaffold(
            Modifier.nestedScroll(nestedScrollConnection),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                       floatingButtonAction()
                    },
                    shape = floatingButtonShape,
                    backgroundColor = BoldBlue,
                    modifier = Modifier
//                            .height(bottomNavHeight)
                        .offset {
                            IntOffset(x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt())
                        }
                ) {
                    Icon(
                        Icons.Filled.Add,
                        tint = Color.White,
                        contentDescription = "Add Items"
                    )
                }
            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,
            bottomBar = {
                BottomAppBar(
                    cutoutShape = floatingButtonShape,
                    modifier = androidx.compose.ui.Modifier
                        .height(bottomNavHeight)
//                            .height(bottomBarHeight)
                        .offset {
                            IntOffset(
                                x = 0,
                                y = -bottomBarOffsetHeightPx.value.roundToInt()
                            )
                        },
                    backgroundColor = BoldBlue,
                    contentColor = BoldGreen,

                    ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    NavigationUnit.drawerItems.forEach { item ->
                        val selected = currentRoute == item.route
                        val textColor = if (selected) Color.White else DarkBlue
                        BottomNavigationItem(
                            icon = {
                                Image(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = item.title,
                                    colorFilter = ColorFilter.tint(textColor),
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontSize = Sizes.textSmall
                                )
                            },
                            selectedContentColor = textColor,
                            unselectedContentColor = textColor,
                            alwaysShowLabel = selected,
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {

                                    navController.graph.startDestinationRoute?.let { screen_route ->
                                        popUpTo(screen_route) {
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
            },
        ) {
            Navigation(navController = navController)
        }
    }

    @Composable
    private fun Navigation(navController: NavHostController) {
        NavHost(
            navController = navController, startDestination =
            currentNavigationItem.route
        ) {
            composable(NavigationItem.Home.route) {
                currentNavigationItem = NavigationItem.Home
//                HomeScreen.Content()
            }
            composable(NavigationItem.CostHistory.route) {
                currentNavigationItem = NavigationItem.CostHistory
                CostHistoryScreen.Content()
//                CostHistoryScreen.Content()
            }
            composable(NavigationItem.IncomeHistory.route) {
                currentNavigationItem = NavigationItem.IncomeHistory
//                IncomeHistoryScreen.Content()
            }
            composable(NavigationItem.Settings.route) {
                currentNavigationItem = NavigationItem.Settings
//                SettingsScreen.Content()
            }
        }
    }
}