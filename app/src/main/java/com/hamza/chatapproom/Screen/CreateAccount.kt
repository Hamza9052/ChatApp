package com.hamza.chatapproom.Screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hamza.chatapproom.DataRoom.UserDao.userdao
import com.hamza.chatapproom.R
import com.hamza.chatapproom.ScreenRoute.Screen
import com.hamza.chatapproom.UserEvent.userEvent
import com.hamza.chatapproom.UserInfo.info
import com.hamza.chatapproom.ViewModel.DataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * author:Hamza Ouaissa
 **/

@Composable
fun Create_Account(
    navController: NavController,
    onEvent: DataViewModel
){
    var showPassword by remember { mutableStateOf(value = false) }
    var user by remember {
        mutableStateOf(info())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.BurlyWood)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.9f))
//        Box (contentAlignment = Alignment.Center){
//            val composition by rememberLottieComposition(
//                LottieCompositionSpec.RawRes(
//                    R.raw.create
//                )
//            )
//            val preloaderProgress by animateLottieCompositionAsState(
//                composition,
//                iterations = LottieConstants.IterateForever,
//                isPlaying = true
//            )
//            LottieAnimation(
//                composition = composition,
//                progress = preloaderProgress,
//                modifier = Modifier.size(200.dp).align(Alignment.Center)
//            )
//
//        }

        Spacer(modifier = Modifier.weight(0.3f))

        OutlinedTextField(
            value = user.Fullname ,
            onValueChange ={
                user = user.copy(
                    Fullname =it
                )
            },label = {
                Text(
                    text = "Full Name",
                    color = colorResource(R.color.DarkSlateBlue),
                    fontWeight = FontWeight.Light
                )
            },
            modifier = Modifier.width(330.dp),
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.DarkSlateBlue)
            ),
            textStyle = TextStyle(
                color = colorResource(R.color.DarkSlateBlue),
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.weight(0.1f))

        OutlinedTextField(
            value = user.email ,
            onValueChange ={
                user = user.copy(
                    email = it
                )
            },label = {
                Text(
                    text = "Email",
                    color = colorResource(R.color.DarkSlateBlue),
                    fontWeight = FontWeight.Light
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.DarkSlateBlue)
            ),
            modifier = Modifier.width(330.dp),
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            textStyle = TextStyle(
                color = colorResource(R.color.Black),
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.weight(0.1f))

        OutlinedTextField(
            value = user.password ,
            onValueChange ={
                user = user.copy(
                    password = it
                )

            },label = {
                Text(
                    text = "Password",
                    color = colorResource(R.color.DarkSlateBlue),
                    fontWeight = FontWeight.Light
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.DarkSlateBlue)
            ),
            modifier = Modifier.width(330.dp),
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            textStyle = TextStyle(
                color = colorResource(R.color.Black),
                fontWeight = FontWeight.Bold
            ),
            visualTransformation = if (showPassword){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            },keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password",
                            tint = colorResource(R.color.HotPink)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password",
                            tint = colorResource(R.color.DarkSlateBlue)
                        )
                    }
                }
            }
        )


        Spacer(modifier = Modifier.weight(0.1f))

        Button(
            onClick = {
                if (
                    user.password.isEmpty() ||
                    user.email.isEmpty() ||
                    user.password.isEmpty() && user.email.isEmpty() ){
                    Toast.makeText(
                        navController.context,
                        "password or email is empty!",
                        Toast.LENGTH_SHORT).show().toString()

                }else {
                    onEvent.onEvent(userEvent.CreateAccount(user = user){state->
                        if (state){
                            navController.navigate(Screen.Login.Route)
                            Toast.makeText(navController.context,"SignUp Is Success", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(navController.context,"SignUp Is Failed", Toast.LENGTH_SHORT).show()
                        }

                    })

                }
            },
            modifier = Modifier
                .width(330.dp)
                .height(40.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors( colorResource(R.color.DarkSlateBlue))

        ) {
            Text(
                text = "Sign Up",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(R.color.BurlyWood)
            )

        }



        Spacer(modifier = Modifier.weight(0.3f))




        Button(
            onClick = {
                navController.navigate(Screen.Login.Route)
            },
            modifier = Modifier
                .width(330.dp)
                .height(40.dp),
            shape = RoundedCornerShape(30.dp),
            contentPadding = ButtonDefaults.ContentPadding,
            colors = ButtonDefaults.buttonColors( colorResource(R.color.DarkSlateBlue))

        ) {
            Text(
                text = "Sign In",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(R.color.BurlyWood)
            )

        }

        Spacer(modifier = Modifier.weight(0.7f))

    }
}