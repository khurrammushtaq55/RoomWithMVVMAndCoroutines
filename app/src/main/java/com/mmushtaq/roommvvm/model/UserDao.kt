package com.mmushtaq.roommvvm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User):Long

    @Query("SELECT * From user where username =:userNAme" )
    suspend fun getUser(userNAme:String):User?

    @Query("DELETE from user where id=:id")
    suspend fun deleteUser(id: Long)


}