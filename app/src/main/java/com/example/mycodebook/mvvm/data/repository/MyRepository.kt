package com.example.mycodebook.mvvm.data.repository
import com.example.mycodebook.mvvm.data.network.AuthResponse
import com.example.mycodebook.mvvm.data.network.MyApi
import retrofit2.Response
class MyRepository {

    suspend fun userLogin(email: String, password: String): Response<AuthResponse>{
       return MyApi().userLogin(email,password)
    }
}