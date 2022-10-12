package com.example.gymresultsapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen.AddEditExerciseScreen
import com.example.gymresultsapp.feature.presentation.exercises_screen.ExerciseScreen
import com.example.gymresultsapp.feature.presentation.utils.Screen
import com.example.gymresultsapp.ui.theme.GymResultsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            GymResultsAppTheme {

                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = true
                    )
                }


                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ExercisesScreen.route
                    ) {
                        composable(
                            route = Screen.ExercisesScreen.route
                        ) {
                            ExerciseScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditExerciseScreen.route +
                                    "?exerciseId={exerciseId}",
                            arguments = listOf(
                                navArgument(
                                    name = "exerciseId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {

                            AddEditExerciseScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
