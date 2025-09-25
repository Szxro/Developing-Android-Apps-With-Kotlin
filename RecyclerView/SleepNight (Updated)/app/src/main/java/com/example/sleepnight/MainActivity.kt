package com.example.sleepnight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sleepnight.ui.quality.SleepQualityRoute
import com.example.sleepnight.ui.quality.SleepQualityScreen
import com.example.sleepnight.ui.theme.SleepNightTheme
import com.example.sleepnight.ui.tracker.TrackerRoute
import com.example.sleepnight.ui.tracker.TrackerScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SleepNightTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController();

                    NavHost(
                        navController = navController,
                        startDestination = TrackerRoute,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable<TrackerRoute> { backStackEntry ->
                            val savedStateHandle = backStackEntry.savedStateHandle;

                            val satisfactionLevelFlow = savedStateHandle.getStateFlow("satisfaction_level", 0);
                            val satisfactionLevel by satisfactionLevelFlow.collectAsState();

                            TrackerScreen(
                                satisfaction = satisfactionLevel,
                                onNavigateToSleepQuality = {
                                    navController.navigate(SleepQualityRoute)
                                }
                            )
                        }

                        composable<SleepQualityRoute>{
                            SleepQualityScreen(
                                onSelectQuality = { quantity ->
                                    // Set the result in Tracker's SavedStateHandle
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("satisfaction_level", quantity);

                                    // Go back to Tracker Route
                                    navController.popBackStack();
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}