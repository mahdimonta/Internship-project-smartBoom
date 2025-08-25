package org.example.project.uiScreens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.navigation.Routes
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import smartboom.composeapp.generated.resources.Res
import smartboom.composeapp.generated.resources.create_an_account
import smartboom.composeapp.generated.resources.forget_password_q
import smartboom.composeapp.generated.resources.hide_ic
import smartboom.composeapp.generated.resources.lock_ic
import smartboom.composeapp.generated.resources.login
import smartboom.composeapp.generated.resources.login_description
import smartboom.composeapp.generated.resources.password
import smartboom.composeapp.generated.resources.password_empty_error
import smartboom.composeapp.generated.resources.password_incorrect_error
import smartboom.composeapp.generated.resources.profile_ic
import smartboom.composeapp.generated.resources.show_ic
import smartboom.composeapp.generated.resources.sign_up
import smartboom.composeapp.generated.resources.username_input_empty_error
import smartboom.composeapp.generated.resources.username_input_incorrect_error
import smartboom.composeapp.generated.resources.username_or_Phone_Number

@Composable
fun Login(navigateTo: (String) -> Unit) {

    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()
    val navigationBarPadding = WindowInsets.navigationBars.asPaddingValues()

    var phone by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf(false) }


    var passWord by remember { mutableStateOf("") }
    var passVisible by remember { mutableStateOf(false) }
    var passError by remember { mutableStateOf(false) }


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
                text =stringResource(Res.string.login),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            )
            Text(
                text = stringResource(Res.string.login_description),
                fontSize = 16.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column {


            CustomTextField(
                stringResource(Res.string.username_or_Phone_Number),
                phone,
                {
                    phone = it.replace(Regex("[^a-zA-Z0-9@.!]"), "")
                    phoneError = it.length < 5 || it == ""
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(30.dp).padding(start = 10.dp).fillMaxWidth(),
                        painter = painterResource(Res.drawable.profile_ic),
                        contentDescription = null,
                        tint = Color(0xff17055e)
                    )
                },
                isError = phoneError,
                errorText = if (phoneError) {
                    if (phone.length < 5) {
                        if (phone != "")
                            stringResource(Res.string.username_input_incorrect_error)
                        else
                            stringResource(Res.string.username_input_empty_error)
                    } else
                        null


                } else null
            )



            Spacer(modifier = Modifier.height(30.dp))




            CustomTextField(
                stringResource(Res.string.password),
                passWord,
                {
                    passWord = it.replace(Regex("[^a-zA-Z0-9@.!]"), "")
                    passError = !isPasswordValid(passWord) || it == "" || it.length < 8

                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(35.dp).padding(start = 10.dp).fillMaxWidth(),
                        painter = painterResource(Res.drawable.lock_ic),
                        contentDescription = null,
                        tint = Color(0xff17055e)
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(end = 10.dp)
                            .fillMaxWidth()
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    passVisible = !passVisible
                                }
                            ),
                        painter = if (passVisible) painterResource(Res.drawable.hide_ic) else painterResource(
                            Res.drawable.show_ic
                        ),
                        contentDescription = null,
                        tint = Color(0xff17055e)
                    )
                },
                visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = passError,
                errorText = if (passError) {
                    if (!isPasswordValid(passWord) || passWord.length < 8) {
                        if (passWord == "")
                            stringResource(Res.string.password_empty_error)
                        else
                            stringResource(Res.string.password_incorrect_error)
                    } else {
                        null
                    }


                } else null
            )
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
                    stringResource(Res.string.login)
                    ,
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
                    stringResource(Res.string.forget_password_q)
                    ,
                    modifier = Modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            navigateTo(Routes.ForgetPass.route)
                        }
                    ),
                    fontSize = 18.sp,
                    color = Color(0xff17055e),
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(

                    stringResource(Res.string.create_an_account)
                    ,
                    modifier = Modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            navigateTo(Routes.SignUp.route)
                        }
                    ),
                    fontSize = 18.sp,
                    color = Color(0xff17055e),
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        }
    }


}