package com.example.month1.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.month1.R
import com.example.month1.ui.data.DataSource
import com.example.month1.ui.data.EventSessionItem
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
    RealLife(title = R.string.real_life_route)
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
        bottomBar = { Month1AppNavigationBar(navController = navController) },
    ) {
        // Implement a NavHost to control navigation between screens
        NavHost(
            navController = navController,
            startDestination = Month1Screens.Theory.name,
            modifier = Modifier.padding(it)
        ) {
            // Define the different routes in the NavGraph
            composable(route = Month1Screens.Theory.name) {
                /* TODO: Theory Screen */
                SessionScreen(
                    pillarList = DataSource.theorySessions
                )
            }

            composable(route = Month1Screens.HandsOn.name) {
                /* TODO: HandsOn Screen */
            }

            composable(route = Month1Screens.RealLife.name) {
                /* TODO: RealLife Screen */
            }

            composable(route = Month1Screens.TellAndShow.name) {
                /* TODO: TellAndShow Screen */
                SessionScreen(
                    pillarList = DataSource.tellAndShowSessions
                )
            }
        }
    }
}

/**
 * filterEventsByCategory discriminates between events by their category
 * @param events list of events in the app [DataSource]
 * @param category the type of event categorised according to session and pillar type
 * @return a list of [EventSessionItem] events
 */
private fun filterEventsByCategory(
    events: List<EventSessionItem>,
    category: String
): List<EventSessionItem> {
    return events.filter { event -> event.category == category }
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
 * Month1AppNavigationBar displays the bottom navigation bar
 * @param navController a NavHostController used to navigate between screens
 */
@Composable
fun Month1AppNavigationBar(
    navController: NavHostController
) {
    NavigationBar(
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            NavigationBarItem(
                icon = R.drawable.baseline_lightbulb_24,
                label = R.string.theory_route,
                onClick = { navController.navigate(Month1Screens.Theory.name) }
            )
            NavigationBarItem(
                icon = R.drawable.baseline_engineering_24,
                label = R.string.hands_on_route,
                onClick = { navController.navigate(Month1Screens.HandsOn.name) }
            )
            NavigationBarItem(
                icon = R.drawable.baseline_insert_chart_24,
                label = R.string.real_life_route,
                onClick = { navController.navigate(Month1Screens.RealLife.name) }
            )
            NavigationBarItem(
                icon = R.drawable.baseline_speaker_notes_24,
                label = R.string.tell_and_show_route,
                onClick = { navController.navigate(Month1Screens.TellAndShow.name) }
            )
        }
    }
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
        modifier = modifier
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