package com.example.month1.ui.screens

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.month1.R
import com.example.month1.ui.Month1Screens
import com.example.month1.ui.data.EventSessionItem
import com.example.month1.ui.model.Month1ViewModel
import com.example.month1.ui.theme.Month1Theme

/**
 * [SessionScreenItem] displays an expandable card of the sessions attended categorised according to
 * the pillar
 * @param pillarName name of the particular session
 * @param pillarDescription brief description of what the pillar is about and its purpose
 * @param navController initialised in [Month1App]
 * @param events list of events of a particular session type under a given pillar
 * @param viewModel the viewModel initialise in [Month1App]
 * @param modifier styling applied to the composable
 */
@Composable
fun SessionScreenItem(
    @StringRes pillarName: Int,
    @StringRes pillarDescription: Int,
    navController: NavHostController,
    events: List<EventSessionItem>,
    viewModel: Month1ViewModel,
    modifier: Modifier = Modifier
) {
    // Internal state used to expand the Card items
    var buttonClicked by rememberSaveable{ mutableStateOf(false) }

    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.medium_padding))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Determine the IconButton to show
                val icon = if (buttonClicked) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

                Text(
                    text = stringResource(id = pillarName),
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { buttonClicked = !buttonClicked }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            }
            if(buttonClicked) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = pillarDescription),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Left
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
                    Button(
                        modifier = Modifier.align(Alignment.End),
                        onClick = {
                            viewModel.updateEvents(events)
                            navController.navigate(Month1Screens.Events.name)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.go_to_sessions),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SessionScreenItemPreview() {
//    Month1Theme {
//        SessionScreenItem(
//            pillarName = R.string.community_engagement_pillar,
//            pillarDescription = R.string.community_engagement_about,
//            modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding))
//        )
//    }
//}