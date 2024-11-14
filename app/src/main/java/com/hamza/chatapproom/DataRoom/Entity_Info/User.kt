package com.hamza.chatapproom.DataRoom.Entity_Info

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hamza.chatapproom.UserInfo.info

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid:Int = 0,
    @ColumnInfo("full_name") val Fullname: String = "",
    @ColumnInfo("email") val Email:String = "",
    @ColumnInfo("password") val Passwrod:String = ""
)
