package com.hamza.chatapproom.DataRoom.UserDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hamza.chatapproom.DataRoom.Entity_Info.User

@Dao
interface userdao {
    @Query("SELECT * FROM User")
    fun getAllUser():List<User>

    /**
     * we put IN for list or set
     */
    @Query("SELECT * FROM User WHERE uid IN (:userIds)")
    fun Documents(userIds:IntArray):List<User>

    /**
     *we put =(equal) for single value
     */
    @Query("SELECT * FROM User Where Email = :mail AND password = :SecretWord")
    fun userLogin(mail: String, SecretWord: String): User

    // vararg it's can use if you want to pass
    // list of value to fun without having to explicitly
    // create a collection (like an array or list)
    @Insert()
    fun insert(vararg user:User)


}