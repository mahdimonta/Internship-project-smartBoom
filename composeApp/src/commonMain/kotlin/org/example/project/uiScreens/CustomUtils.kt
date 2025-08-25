package org.example.project.uiScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AlertDialog(
    showDialog: Boolean,
    onDestroy: () -> Unit,
    buttonsCount: Int,
    buttonsName: List<String>,
    buttonsColor: List<Color>,
    buttonsAction: List<() -> Unit>,
    dialogText: String
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = { onDestroy() },

            )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    ),

                ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp, start = 15.dp, bottom = 15.dp, top = 10.dp),
                    text = dialogText,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp, start = 10.dp),
                    thickness = 1.dp,
                    color = Color(0xffdddddd)


                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp, start = 15.dp, bottom = 15.dp, top = 10.dp),

                    ) {
                    for (i in 0..buttonsCount - 1) {
                        Text(
                            modifier = Modifier
                                .clickable(
                                    onClick = buttonsAction[i]

                                ),
                            text = buttonsName[i],
                            color = buttonsColor[i],
                            fontSize = 17.sp
                        )
                    }
                }

            }

        }
    }

}

@Composable
fun CustomTextField(
    upText: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean  = false,
    errorText : String? =null,
    visualTransformation : VisualTransformation = VisualTransformation.None,
    shape : Shape = RoundedCornerShape(35.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = upText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textDirection = TextDirection.Ltr
            ),
            keyboardOptions = keyboardOptions,
            shape = shape,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedBorderColor = Color(0xff17055e),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF1B3C86)
            ),
            isError = isError,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(horizontal = 0.dp),
            visualTransformation = visualTransformation,


        )
        if (errorText != null){
            Text(
                text = errorText,
                fontSize = 14.sp,
                color = Color.Red,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
            )
        }
    }
}

fun isPasswordValid(password: String): Boolean {
    val regex = Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$")
    return regex.matches(password)
}