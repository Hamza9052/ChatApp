package com.hamza.chatapproom.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.chatapproom.DataRoom.Entity_Info.User
import com.hamza.chatapproom.DataRoom.UserDao.userdao
import com.hamza.chatapproom.UserEvent.userEvent
import com.hamza.chatapproom.UserInfo.info
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
/**
 * author:Hamza Ouaissa
 **/

class DataViewModel(private val dao: userdao): ViewModel() {

    private val _state = MutableStateFlow(info())


    fun onEvent(event: userEvent){
        when(event){
            is userEvent.Login -> {

            }
            is userEvent.SignOut -> {

            }
            is userEvent.CreateAccount -> create_account(event.user,event.state)
        }
    }

    fun create_account(user: info, state: (Boolean) -> Unit) {

        viewModelScope.launch {
            if (dao.checkEmail(user.email) == null) {
                val infoUser = User(Fullname = user.Fullname, Email = user.email, Passwrod = user.password)
                dao.insert(infoUser)
                Log.e("create_Account","Create Account is successful")
                state(true)
            }else{
                Log.e("create_Account","this email is available")
                state(false)
            }
        }
    }


}