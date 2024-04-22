package com.example.month1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.month1.R
import com.example.month1.ui.Month1Screens
import com.example.month1.ui.data.DataSource
import com.example.month1.ui.data.EventSessionItem
import com.example.month1.ui.data.PillarItem
import com.example.month1.ui.model.Month1ViewModel
import com.example.month1.ui.theme.Month1Theme

@Composable
fun SessionScreen(
    pillarList: List<PillarItem>,
    pillarEvents: List<EventSessionItem>,
    navController: NavHostController,
    viewModel: Month1ViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.medium_padding))
    ) {
        BigPicture()
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(pillarList) {
                SessionScreenItem(
                    pillarName = it.pillarName,
                    pillarDescription = it.pillarDescription,
                    navController = navController,
                    events = filterEventsByCategory(
                        events = pillarEvents,
                        category = stringResource(id = it.pillarName)
                    ),
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
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


//@Preview(showBackground = true)
//@Composable
//fun SessionScreenPreview() {
//    Month1Theme {
//        SessionScreen(
//            pillarList = DataSource.theorySessions,
//            pillarEvents = DataSource.tellAndShowEvents
//        )
//    }
//}
