package com.example.month1.ui

import com.example.month1.ui.data.EventSessionItem

/**
 * [Month1State] describes the state variables of [Month1ViewModel]
 */
data class Month1State(
    val currentEvents: List<EventSessionItem> = listOf(),
    val currentSelectedNavBarIcon: Month1Screens = Month1Screens.Theory
)
