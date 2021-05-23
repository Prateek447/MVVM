package com.example.mycodebook.mvvm.ui.auth

import androidx.lifecycle.ViewModel
import com.example.mycodebook.mvvm.data.db.entites.User
import com.example.mycodebook.mvvm.data.repository.UserRepository


class UserViewModel(private val repository: UserRepository) : ViewModel() {


    fun getLoggedInUser() =  repository.getUser()


    suspend fun userLogin(email: String, password : String)  = repository.userLogin(email,password)

    suspend fun saveLoggedInUser(user: User) =  repository.saveUser(user)

}