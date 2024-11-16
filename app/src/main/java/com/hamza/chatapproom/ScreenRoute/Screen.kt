package com.hamza.chatapproom.ScreenRoute

sealed class Screen (val Route:String){
    object Login:Screen("login")
    object CreateAccount:Screen("CreateAcoount")
    object MainScreen:Screen("mainScreen")
}