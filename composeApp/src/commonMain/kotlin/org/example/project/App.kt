package org.example.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.navigation.Routes
import org.example.project.uiScreens.ForgetPass
import org.example.project.uiScreens.Login
import org.example.project.uiScreens.LoginOrSignUp
import org.example.project.uiScreens.SignUp
import org.example.project.uiScreens.SplashScreen
import org.example.project.uiScreens.Verification
import org.example.project.viewmodel.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {


    val navController = rememberNavController()
    var uiTrigger by remember { mutableStateOf(0) }
    val viewModel = AppViewModel()
    val layoutDirection = viewModel.layoutDirection.collectAsState().value
    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {

        key(uiTrigger) {


            NavHost(
                modifier = Modifier
                    .fillMaxSize(),
                navController = navController,
                startDestination = Routes.Splash.route
            ) {

                composable(Routes.Splash.route) {
                    SplashScreen(navigateTo = { route ->
                        navController.navigate(route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    })

                }
                composable(Routes.LoginOrSignUp.route) {
                    LoginOrSignUp(
                        navigateTo = { route ->
                            navController.navigate(route) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                        },
                        viewModel
                    )

                }
                composable(Routes.Login.route) {
                    Login { route ->
                        navController.navigate(route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                }
                composable(Routes.SignUp.route) {
                    SignUp { route ->
                        navController.navigate(route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                }
                composable(Routes.ForgetPass.route) {
                    ForgetPass { route ->
                        navController.navigate(route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                }

                composable(Routes.Verification.route) {
                    Verification { route ->
                        navController.navigate(route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }

}