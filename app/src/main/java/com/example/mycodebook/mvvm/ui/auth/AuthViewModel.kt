package com.example.mycodebook.mvvm.ui.auth

import androidx.lifecycle.ViewModel
import com.example.mycodebook.mvvm.data.db.entites.User
import com.example.mycodebook.mvvm.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AuthViewModel(private val repository: UserRepository) : ViewModel() {


    fun getLoggedInUser() =  repository.getUser()


    suspend fun userLogin(email: String, password : String)  = repository.userLogin(email,password)


    suspend fun userSignnUp(name : String, email: String, password : String)
    = withContext(Dispatchers.IO){repository.userSignUp(name,email, password)}

    suspend fun saveLoggedInUser(user: User) = withContext(Dispatchers.IO){repository.saveUser(user)}

}