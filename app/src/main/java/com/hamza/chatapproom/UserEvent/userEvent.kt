package com.hamza.chatapproom.UserEvent


import com.hamza.chatapproom.DataRoom.Entity_Info.User
import com.hamza.chatapproom.DataRoom.UserDao.userdao
import com.hamza.chatapproom.UserInfo.info

interface userEvent {
    data class Login(var user: info):userEvent
    data class CreateAccount(var user: info):userEvent
    data class SignOut(var user: info):userEvent


}

//,val list: User