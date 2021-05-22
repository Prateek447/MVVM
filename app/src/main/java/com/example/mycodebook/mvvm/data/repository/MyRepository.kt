package com.example.mycodebook.mvvm.data.repository
import com.example.mycodebook.mvvm.data.network.AuthResponse
import com.example.mycodebook.mvvm.data.network.MyApi
import com.example.mycodebook.mvvm.data.network.SafeApiRequest
import retrofit2.Response
class MyRepository : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse{

        return apiRequest { MyApi().userLogin(email, password) }
    }
}