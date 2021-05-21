package com.example.mycodebook.mvvm.data.network

import com.example.mycodebook.mvvm.data.db.entites.User


data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)