package com.hamza.chatapproom.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.hamza.chatapproom.DataRoom.Entity_Info.User
import com.hamza.chatapproom.DataRoom.UserDao.userdao
import com.hamza.chatapproom.UserEvent.userEvent
import com.hamza.chatapproom.UserInfo.info
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DataViewModel(private val dao: userdao): ViewModel() {


    private val _state = MutableStateFlow(info())


    fun onEvent(event: userEvent){
        when(event){
            is userEvent.Login -> {

            }
            is userEvent.SignOut -> {

            }
            is userEvent.CreateAccount -> {
                val infoUser = User(Fullname = _state.value.Fullname, Email = _state.value.email, Passwrod = _state.value.password)
                viewModelScope.launch {
                  if (dao.checkEmail(_state.value.email) == null) {
                      dao.insert(infoUser)
                      Log.e("create_Account","Create Account is successful")
                    }else{
                        Log.e("create_Account","this email is available")
                    }
                }
            }
        }
    }











}