package com.example.month1.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import com.example.month1.R
import com.example.month1.ui.model.Month1ViewModel


data class NavigationBarIcon(
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val destination: Month1Screens
)


/**
 * Displays the content of the Bottom NavigationBar
 * @param navController navigation controller initialised in [Month1App]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Month1AppNavigationBar(
    navController: NavHostController,
    viewModel: Month1ViewModel,
    currentScreen: Month1Screens
) {
    val items = listOf(
        NavigationBarIcon(
            icon = R.drawable.baseline_lightbulb_24,
            label = R.string.theory_route,
            destination = Month1Screens.Theory
        ),
        NavigationBarIcon(
            icon = R.drawable.baseline_engineering_24,
            label = R.string.hands_on_route,
            destination = Month1Screens.HandsOn
        ),
        NavigationBarIcon(
            icon = R.drawable.baseline_insert_chart_24,
            label = R.string.real_life_route,
            destination = Month1Screens.RealLife
        ),
        NavigationBarIcon(
            icon = R.drawable.baseline_speaker_notes_24,
            label = R.string.tell_and_show_route,
            destination = Month1Screens.TellAndShow
        )
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.destination == currentScreen,
                onClick = {
                    viewModel.updateSelectedIcon(item.destination)
                    navController.navigate(item.destination.name)
                },
                label = {
                    Text(
                        text = stringResource(item.label),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                alwaysShowLabel = true,
                icon = {
                    BadgedBox(
                        badge = { }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(item.icon),
                            contentDescription = stringResource(id = item.label),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            )
        }
    }
}