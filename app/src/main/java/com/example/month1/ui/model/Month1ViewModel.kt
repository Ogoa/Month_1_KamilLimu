package com.example.month1.ui.model

import androidx.lifecycle.ViewModel
import com.example.month1.ui.Month1Screens
import com.example.month1.ui.Month1State
import com.example.month1.ui.data.EventSessionItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Month1ViewModel is the ViewModel layer of the UI layer
 * It exposes state to the UI components and receives UI events
 */
class Month1ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(Month1State())
    var uiState: StateFlow<Month1State> = _uiState.asStateFlow()


    /**
     * Updates the events to be displayed in a particular [EventScreen]
     * @param events the events of a particular session type, under a given pillar
     */
    fun updateEvents(events: List<EventSessionItem>) {
        _uiState.update { currentState ->
            currentState.copy(
                currentEvents = events
            )
        }
    }
}