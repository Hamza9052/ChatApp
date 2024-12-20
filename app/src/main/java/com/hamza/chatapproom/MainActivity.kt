package com.hamza.chatapproom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.hamza.chatapproom.DataRoom.AppDatabase

import com.hamza.chatapproom.Screen.Create_Account
import com.hamza.chatapproom.Screen.Login
import com.hamza.chatapproom.Screen.MainScreen
import com.hamza.chatapproom.ScreenRoute.Screen
import com.hamza.chatapproom.ViewModel.DataViewModel
import com.hamza.chatapproom.ui.theme.ChatAppDBTheme

/**
 *
 * By the way this method of building instance
 * it's not able with large or medium projects
 * when you want to start with medium or large
 * project u should use the Dagger Module
 *
 * author:Hamza Ouaissa
 *
 **/


class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "App.db"
        ).build()
    }
    private val dataVM by viewModels<DataViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DataViewModel(db.Userdao()) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppDBTheme{
                        val navController = rememberNavController()


                NavHost(navController, startDestination =Screen.Login.Route ){

                    composable(
                        Screen.Login.Route){
                        Login(navController,dataVM)
                    }
                    composable(
                        Screen.CreateAccount.Route){
                        Create_Account(navController, onEvent = dataVM)
                    }
                    composable(
                        Screen.MainScreen.Route){
                        MainScreen(dataVM,navController)
                    }
                }

            }



        }
    }
}

