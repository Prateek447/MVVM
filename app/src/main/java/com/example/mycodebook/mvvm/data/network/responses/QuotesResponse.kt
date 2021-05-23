package com.example.mycodebook.mvvm.data.network.responses

import com.example.mycodebook.mvvm.data.db.entites.Quotes

data class QuotesResponse(

    val isSuccessful : Boolean,
    val quotes: List<Quotes>
)
