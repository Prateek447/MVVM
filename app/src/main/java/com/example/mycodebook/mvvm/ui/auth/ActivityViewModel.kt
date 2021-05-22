package com.example.mycodebook.mvvm.ui.auth
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mycodebook.mvvm.data.repository.MyRepository
import com.example.mycodebook.mvvm.util.ApiExceptions
import net.simplifiedcoding.mvvmsampleapp.util.Coroutines

class ActivityViewModel(private val repository: MyRepository) : ViewModel() {

    var email : String? = null
    var password : String? = null

    var authListener: AuthListener? =null

    // fun to get the user from the database
    fun getLoggedInUser() =  repository.getUser()

    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Failure occure")
            return
        }
        //Again this is bad practice we should not create instance of a class is inside of another class
        //beacause this result in tight coupling which is bad practice we can use dependency injection
        //for loosly coupled which is good practice
//        val loginResponse =  MyRepository().userLogin(email!!,password!!)
//        authListener?.onSuccess(loginResponse)

        Coroutines.main {
            try {
                val response = repository.userLogin(email!!,password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(response.message!!)
            }
            catch (e : ApiExceptions){
                authListener?.onFailure(e.toString())
            }
        }
    }

}