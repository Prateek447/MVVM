package com.example.mycodebook.mvvm.ui.auth

import com.example.mycodebook.mvvm.data.db.entites.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User?)
    fun onFailure(message : String)
}