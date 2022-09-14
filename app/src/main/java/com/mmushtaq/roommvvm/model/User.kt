package com.mmushtaq.roommvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    val userName:String,
    @ColumnInfo(name = "password_hash")
    val passwordHash:Int,
) {

    @PrimaryKey(autoGenerate = true)
    var id:Long=0
}