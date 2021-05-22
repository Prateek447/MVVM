package com.example.mycodebook.mvvm.data.repository
import com.example.mycodebook.mvvm.data.db.AppDataBase
import com.example.mycodebook.mvvm.data.db.entites.User
import com.example.mycodebook.mvvm.data.network.AuthResponse
import com.example.mycodebook.mvvm.data.network.MyApi
import com.example.mycodebook.mvvm.data.network.SafeApiRequest

class MyRepository(private val api: MyApi, private val db: AppDataBase) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse{
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun saveUser(user: User) =  db.getUserDao().upsert(user)

    fun getUser() =  db.getUserDao().getUser()
}