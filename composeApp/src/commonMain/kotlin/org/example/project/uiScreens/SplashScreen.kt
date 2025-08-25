package org.example.project.uiScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import kotlinx.coroutines.delay
import org.example.project.expectClass.ConnectivityChecker
import org.example.project.expectClass.exitApp
import org.example.project.navigation.Routes
import smartboom.composeapp.generated.resources.Res

@Composable
fun SplashScreen(navigateTo: (String) -> Unit) {
    var netState by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    if(!showDialog){
        LaunchedEffect(Unit) {
            netState = ConnectivityChecker().isNetworkAvailable()
            delay( 3000)
            if (netState) {
                navigateTo(Routes.LoginOrSignUp.route)
            } else {
                showDialog = true

            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xff04045e)
            )
    ) {
        val composition by rememberLottieComposition {
            LottieCompositionSpec.JsonString(
                Res.readBytes("files/loadingAnimation.json").decodeToString()
            )
        }



        AlertDialog(
            showDialog = showDialog,
            onDestroy = { showDialog = false },
            buttonsCount = 2,
            buttonsName = listOf("Exit App", "Retry"),
            buttonsColor = listOf(Color.Black,Color.Blue),
            buttonsAction = listOf({
                exitApp()
            }, {
                showDialog = false

            }),
            dialogText = "No internet connection"
        )

            Image(
                modifier = Modifier.align(alignment = Alignment.BottomCenter),
                painter =  rememberLottiePainter(
                    composition = composition,
                    iterations = Compottie.IterateForever,
                ),
                contentDescription = "Lottie animation"
            )

    }
}