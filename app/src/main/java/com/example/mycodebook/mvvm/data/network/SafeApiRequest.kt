package com.example.mycodebook.mvvm.data.network

import com.example.mycodebook.mvvm.util.ApiExceptions
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

       suspend fun<T: Any> apiRequest( call: suspend() -> Response<T>): T{
           try {
               val response = call.invoke()
               if (response.isSuccessful){
                   return response.body()!!
               }
               else{
                   val error = response.errorBody()?.toString()
                   val message  =  StringBuilder()
                   error?.let {
                       try {
                           message.append(JSONObject(it).getString("message"))
                       }
                       catch (e : JSONException){
                           message.append("\n")
                       }
                   }
                   message.append("Error code ${response.code()}")
                   throw ApiExceptions(message.toString())
               }
           }catch (e : Exception){
               throw ApiExceptions(e.message.toString())
           }

       }
}