package com.hamza.chatapproom.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.chatapproom.DataRoom.UserDao.User
import com.hamza.chatapproom.DataRoom.UserDao.userdao
import com.hamza.chatapproom.UserEvent.userEvent
import com.hamza.chatapproom.UserInfo.info
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
/**
 * author:Hamza Ouaissa
 **/

class DataViewModel(private val dao: userdao): ViewModel() {

    private val _state = MutableStateFlow(info())

    private var _user = MutableStateFlow(mutableListOf<String>())
    val users: StateFlow<List<String>> = _user.asStateFlow()




    init {
        USERS()
    }



    fun onEvent(event: userEvent){
        when(event){
            is userEvent.Login -> login(event.user,event.state)
            is userEvent.SignOut -> signout()
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

     fun login(user: info, state: (Boolean) -> Unit,) {

        viewModelScope.launch{
            val use = dao.checkUser(user.email,user.password)
            if (use == true){
                dao.userLogin(user.email,user.password)
                state(true)
                Log.e("LogIn","LogIn is successful")
            }else{
                state(false)
                Log.e("LogIn","LogIn is Failed")
            }

        }

    }


   private fun USERS(){

        viewModelScope.launch{
            dao.allUserNames().let {
                dao.allUserNames().forEach{
                    _user.value.add(it)
                }
            }


        }

    }



     fun signout() {

    }





}