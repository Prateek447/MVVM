package com.example.mycodebook.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mycodebook.mvvm.data.db.entites.CURRENT_USER_ID
import com.example.mycodebook.mvvm.data.db.entites.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User): Long


    @Query("SELECT * FROM User WHERE uid = $CURRENT_USER_ID")
    fun get(): LiveData<User>
}