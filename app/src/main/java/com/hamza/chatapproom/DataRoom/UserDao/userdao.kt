package com.hamza.chatapproom.DataRoom.UserDao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface userdao {

    @Query("SELECT * FROM User")
   suspend fun getAllUser():List<User>
    /**
     * we put IN for list or set
     */
    @Query("SELECT * FROM User WHERE uid IN (:userIds)")
    suspend fun Documents(userIds:IntArray):List<User>

    /**
     *we put =(equal) for single value
     */
    @Query("SELECT * FROM User Where Email = :mail AND password = :SecretWord")
    suspend fun userLogin(mail: String, SecretWord: String): User


    @Query("SELECT full_name From user")
    suspend fun allUserNames():List<String>


    // vararg it's can use if you want to pass
    // list of value to fun without having to explicitly
    // create a collection (like an array or list)
    @Insert()
    suspend fun insert(vararg user:User)


    /**
     * Check Email is it excited or not
     **/

    @Query("SELECT Email FROM User Where Email = :check")
    suspend fun checkEmail(check: String):String?

    /**
     * Check email && password is it excited or not
     **/
    @Query("SELECT EXISTS(SELECT 1 FROM User WHERE Email == :checkEmail AND password == :checkPassword)")
    suspend fun CHECK(checkEmail:String,checkPassword:String): Boolean




    @Delete
    suspend fun deleteEmptyUser(vararg user:User)
}