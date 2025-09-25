package com.example.sleepnight.ui.tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleepnight.data.persistence.entities.SleepNight
import com.example.sleepnight.data.repository.SleepNightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TrackerViewModel @Inject constructor(
    private val sleepNightRepository: SleepNightRepository,
) : ViewModel() {
    val currentNight = MutableStateFlow<SleepNight?>(null);

    val nights = MutableStateFlow<List<SleepNight>>(emptyList());

    init {
        viewModelScope.launch {
            currentNight.value = getCurrentNight();
            nights.value = getAllNights();
        }
    }

    fun onStartTracking(): Unit {
        viewModelScope.launch {
            if(currentNight.value != null) return@launch;

            val newNight = SleepNight();

            insertNewNight(newNight);

            currentNight.value = getCurrentNight();
        }
    }

    fun onStopTracking(quality: Int): Unit {
        viewModelScope.launch {
            val oldNight = currentNight.value ?: return@launch;

            oldNight.endTimeMilliSeconds = System.currentTimeMillis();

            oldNight.sleepQuality = quality;

            updateNight(oldNight);

            currentNight.value = null;

            nights.value = getAllNights();
        }
    }

    fun onClearTracking(): Unit {
        viewModelScope.launch {
            clearAll();

            currentNight.value = null;
            nights.value = getAllNights();
        }
    }

    // Long-running operations
    private suspend fun getCurrentNight(): SleepNight? {
        return withContext(Dispatchers.IO) {
            var night = sleepNightRepository.getTonightSleep();

            if (night?.startTimeMilliSeconds != night?.endTimeMilliSeconds) {
                night = null
            }
            // returning the night variable
            night
        }
    }

    private suspend fun getAllNights(): List<SleepNight> {
        return withContext(Dispatchers.IO) {
            sleepNightRepository.getAllNights();
        }
    }

    private suspend fun insertNewNight(night: SleepNight): Unit {
        withContext(Dispatchers.IO) {
            sleepNightRepository.insertSleepNight(night);
        }
    }

    private suspend fun updateNight(night: SleepNight): Unit {
        withContext(Dispatchers.IO) {
            sleepNightRepository.updateSleepNight(night);
        }
    }

    private suspend fun clearAll(): Unit {
        withContext(Dispatchers.IO) {
            sleepNightRepository.deleteAll();
        }
    }
}