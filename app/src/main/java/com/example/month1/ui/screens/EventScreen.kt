package com.example.month1.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.month1.R
import com.example.month1.ui.data.DataSource
import com.example.month1.ui.data.EventSessionItem
import com.example.month1.ui.theme.Month1Theme

/**
 * EventScreen displays the contents of an event
 * @param events a list of events under the particular [SessionScreen]
 * @param modifier styling and behaviour applied to the composable
 */
@Composable
fun EventScreen(
    events: List<EventSessionItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(events) {
            EventScreenItem(
                eventTitle = it.eventTitle,
                eventTakeaways = it.eventTakeaways,
                eventApplicationNotes = it.eventApplicationNotes,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.small_padding))
            )
        }
    }
}

/**
 * EventScreenItem displays details of an event
 * @param eventTitle topic of the event
 * @param eventTakeaways two things learnt from the event session
 * @param eventApplicationNotes two things you plan to apply from what you've learnt during the event
 */
@Composable
fun EventScreenItem(
    @StringRes eventTitle: Int,
    @StringRes eventTakeaways: Int,
    @StringRes eventApplicationNotes: Int,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.medium_padding))
        ) {
            Text(
                text = stringResource(id = eventTitle),
                style = MaterialTheme.typography.bodyLarge
            )
            Divider()
            EventScreenItemNotes(
                header = R.string.key_takeaways,
                notes = stringResource(eventTakeaways)
            )
            EventScreenItemNotes(
                header = R.string.apply,
                notes = stringResource(eventApplicationNotes)
            )
        }
    }
}

/**
 * EventScreenItemNotes displays the notes on key takeaways learnt from an event
 * as well as how you plan on applying them
 */
@Composable
fun EventScreenItemNotes(
    @StringRes header: Int,
    notes: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
        Text(
            text = stringResource(id = header),
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
        Text(
            text = notes,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
    }
}


@Preview(showBackground = true)
@Composable
fun EventScreenPreview() {
    Month1Theme(darkTheme = false) {
        EventScreen(events = DataSource.theoryEvents)
    }
}