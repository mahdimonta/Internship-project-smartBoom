package org.example.project.uiScreens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.navigation.Routes
import org.example.project.viewmodel.AppViewModel
import org.jetbrains.compose.resources.stringResource
import smartboom.composeapp.generated.resources.Res
import smartboom.composeapp.generated.resources.dont_have_account
import smartboom.composeapp.generated.resources.login
import smartboom.composeapp.generated.resources.sign_up

@Composable
fun LoginOrSignUp(navigateTo: (String) -> Unit, viewModel: AppViewModel) {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()
    val navigationBarPadding = WindowInsets.navigationBars.asPaddingValues()
    var isBoxVisible by remember { mutableStateOf(false) }
    val size by animateDpAsState(
        targetValue = if (isBoxVisible) 150.dp else 0.dp,
        animationSpec = tween(durationMillis = 500),
    )

    val currentLang = viewModel.currentLanguage.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(
                color = Color(0xff04045e)
            )
            .padding(
                bottom = navigationBarPadding.calculateBottomPadding(),
                top = statusBarPadding.calculateTopPadding()
            )
            .clickable(
                onClick = {
                    isBoxVisible = false

                },
                indication = null,
                interactionSource = MutableInteractionSource()

            )

    ) {
        Text(

            text = if (currentLang == "en") "English" else "فارسی",
            fontSize = 18.sp,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(top = 15.dp)
                .clickable(
                    onClick = {
                        isBoxVisible = true

                    }
                ),
            color = Color.White
        )

        Column(
            modifier = Modifier
                .width(100.dp)
                .height(size)
                .align(Alignment.TopCenter)
                .padding(top = 15.dp)
                .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            if (isBoxVisible) {
                Text(
                    text = "English",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.clickable(
                        onClick = {
                            isBoxVisible = false

                            if (currentLang != "en") {
                                viewModel.switchToEnglish()
                            }
                        }
                    )
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp, start = 10.dp),
                    thickness = 1.dp,
                    color = Color(0xffdddddd)


                )
                Text(
                    text = "فارسی",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.clickable(
                        onClick = {

                            isBoxVisible = false
                            if (currentLang == "en") {
                                viewModel.switchToPersian()
                            }
                        }
                    )
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navigateTo(Routes.Login.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff043d87)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(end = 15.dp, start = 15.dp)

            ) {
                Text(
                    stringResource(Res.string.login),
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(Res.string.dont_have_account),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(Res.string.sign_up),
                    modifier = Modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            navigateTo(Routes.SignUp.route)
                        }
                    ),
                    fontSize = 18.sp,
                    color = Color.White,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        }
    }
}