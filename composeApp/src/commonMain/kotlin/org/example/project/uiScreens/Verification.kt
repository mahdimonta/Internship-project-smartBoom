package org.example.project.uiScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import smartboom.composeapp.generated.resources.Res
import smartboom.composeapp.generated.resources.code_sent
import smartboom.composeapp.generated.resources.confirm
import smartboom.composeapp.generated.resources.login
import smartboom.composeapp.generated.resources.verification


@Composable
fun Verification(navigateTo: (String) -> Unit) {

    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()
    val navigationBarPadding = WindowInsets.navigationBars.asPaddingValues()

    val codeValues = remember { mutableStateListOf("", "", "", "", "") }
    val focusRequesters = List(codeValues.size) { FocusRequester() }


    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

            .background(
                color = Color(0xfff0f0f0)
            )
            .padding(
                bottom = navigationBarPadding.calculateBottomPadding(),
                top = statusBarPadding.calculateTopPadding()
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            },
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                text = stringResource(Res.string.verification),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            )
            Text(
                text = stringResource(Res.string.code_sent),
                fontSize = 16.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column {

            LaunchedEffect(Unit) {
                focusRequesters.first().requestFocus()
            }


            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {


                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 30.dp, start = 30.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    codeValues.forEachIndexed { index, value ->
                        OutlinedTextField(
                            value = value,
                            onValueChange = { newValue ->
                                if (newValue.length <= 1) {
                                    codeValues[index] = newValue
                                    when {
                                        newValue.isNotEmpty() && index < codeValues.lastIndex -> {
                                            focusRequesters[index + 1].requestFocus()
                                        }

                                        newValue.isEmpty() && index > 0 -> {
                                            focusRequesters[index - 1].requestFocus()
                                        }
                                    }
                                }
                            },
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                textDirection = TextDirection.Ltr

                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedBorderColor = Color(0xff17055e),
                                unfocusedBorderColor = Color.Gray,
                                cursorColor = Color(0xFF1B3C86),
                            ),
                            modifier = Modifier
                                .width(50.dp)
                                .height(55.dp)
                                .focusRequester(focusRequesters[index])
                                .onPreviewKeyEvent { event ->
                                    if (event.type == KeyEventType.KeyDown && event.key == Key.Backspace) {
                                        if (codeValues[index].isEmpty() && index > 0) {
                                            focusRequesters[index - 1].requestFocus()
                                        }
                                    }
                                    false

                                }

                        )
                    }

                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff17055e)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(end = 15.dp, start = 15.dp)

            ) {
                Text(
                    stringResource(Res.string.confirm),
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }


}