package com.example.month1.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.month1.R
import com.example.month1.ui.data.DataSource
import com.example.month1.ui.model.Month1ViewModel
import com.example.month1.ui.screens.EventScreen
import com.example.month1.ui.screens.SessionScreen
import com.example.month1.ui.theme.Month1Theme


/**
 * [Month1Screens] is an enum class that defines destinations for different routes
 */
enum class Month1Screens(@StringRes val title: Int) {
    // According to Session types
    Theory(title = R.string.theory_route),
    TellAndShow(title = R.string.tell_and_show_route),
    HandsOn(title = R.string.hands_on_route),
    RealLife(title = R.string.real_life_route),

    Events(title = R.string.events)
}

/**
 * [Month1App] is the parent composable containing all other screens
 */
@Composable
fun Month1App(
    viewModel: Month1ViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Month1Screens.valueOf(
        backStackEntry?.destination?.route ?: Month1Screens.Theory.name
    )

    Scaffold(
        topBar = {
            Month1AppBar(
                currentScreen = currentScreen, /*TODO: Look whether this will need to change*/
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            Month1AppNavigationBar(
                navController = navController,
                viewModel = viewModel,
                currentScreen = currentScreen
            )
        }
    ) {
        // Implement a NavHost to control navigation between screens
        NavHost(
            navController = navController,
            startDestination = Month1Screens.Theory.name,
            modifier = Modifier.padding(it)
        ) {
            // Define the different routes in the NavGraph
            composable(route = Month1Screens.Theory.name) {
                SessionScreen(
                    pillarList = DataSource.theorySessions,
                    pillarEvents = DataSource.theoryEvents,
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(route = Month1Screens.HandsOn.name) {
                /* TODO: HandsOn Screen */
            }

            composable(route = Month1Screens.RealLife.name) {
                /* TODO: RealLife Screen */
            }

            composable(route = Month1Screens.TellAndShow.name) {
                SessionScreen(
                    pillarList = DataSource.tellAndShowSessions,
                    pillarEvents = DataSource.tellAndShowEvents,
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(route = Month1Screens.Events.name) {
                EventScreen(
                    events = uiState.currentEvents,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding))
                )
            }
        }
    }
}


/**
 * Month1AppBar displays the top app bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Month1AppBar(
    currentScreen: Month1Screens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
        ) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    )
}


/**
 * NavigationBarItem contains a single item in the navigation bar
 * @param icon icon to be displayed
 * @param label the string to be displayed
 * @param onClick event to be passed to the viewmodel when the icon is clicked
 * @param modifier styling applied to the composable
 */
@Composable
fun NavigationBarItem(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        IconButton(onClick = onClick ) {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null
            )
        }
        Text(
            text = stringResource(id = label),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Month1AppPreview() {
    Month1Theme(darkTheme = true) {
        Month1App()
    }
}