package com.hamza.chatapproom.UserEvent


import com.hamza.chatapproom.UserInfo.info

interface userEvent {
    data class Login(var user: info,var state: (Boolean) -> Unit):userEvent
    data class CreateAccount(var user: info,var state: (Boolean) -> Unit):userEvent
    data class SignOut(var user: info):userEvent

}
