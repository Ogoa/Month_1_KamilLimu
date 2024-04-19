package com.example.month1.ui.data

import androidx.annotation.StringRes

/**
 * [EventSessionItem] describes the detail of an event session under a particular session and pillar
 * type
 * @param eventTitle title of the event
 * @param eventTakeaways two key points learnt from the event
 * @param eventApplicationNotes two notes on how you plan to apply what has been learnt during the
 * event
 */
data class EventSessionItem(
    @StringRes val eventTitle: Int,
    @StringRes val eventTakeaways: Int,
    @StringRes val eventApplicationNotes: Int,
    val category: String
)
