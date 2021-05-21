package com.example.mycodebook.mvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mycodebook.mvvm.data.repository.MyRepository

class ActivityViewModel : ViewModel() {

    var email : String? = null
    var password : String? = null

    var authListener: AuthListener? =null

    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Failure occure")
            return
        }
        //Again this is bad practice we should not create instance of a class is inside of another class
        //beacause this result in tight coupling which is bad practice we can use dependency injection
        //for loosly coupled which is good practice
        val loginResponse =  MyRepository().userLogin(email!!,password!!)
        authListener?.onSuccess(loginResponse)
    }

}