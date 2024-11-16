package com.hamza.chatapproom.DataRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamza.chatapproom.DataRoom.Entity_Info.User
import com.hamza.chatapproom.DataRoom.UserDao.userdao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun Userdao(): userdao

}

