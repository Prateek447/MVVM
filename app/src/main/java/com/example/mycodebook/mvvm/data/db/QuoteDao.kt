package com.example.mycodebook.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mycodebook.mvvm.data.db.entites.Quotes


@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quotes: List<Quotes>)

    @Query("SELECT * FROM quotes")
    fun getQuotes() : LiveData<List<Quotes>>


}