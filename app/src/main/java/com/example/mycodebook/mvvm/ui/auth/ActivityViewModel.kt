package com.example.mycodebook.mvvm.ui.auth
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mycodebook.mvvm.data.repository.MyRepository
import com.example.mycodebook.mvvm.util.ApiExceptions
import com.example.mycodebook.mvvm.util.NoInternetException
import net.simplifiedcoding.mvvmsampleapp.util.Coroutines

class ActivityViewModel(private val repository: MyRepository) : ViewModel() {

    var email : String? = null
    var password : String? = null
    var name : String? = null
    var passwordConfirm: String? = null

    var authListener: AuthListener? =null

    // fun to get the user from the database
    fun getLoggedInUser() =  repository.getUser()

    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Fields are empty")
            return
        }
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
            catch (e : NoInternetException){
                authListener?.onFailure(e.message.toString())
            }
        }
    }
    fun onSignUpButtonClick(view : View){
        authListener?.onStarted()
        if (name.isNullOrEmpty()){
            authListener?.onFailure("Plz provide a name")
            return
        }
        if(email.isNullOrEmpty()){
            authListener?.onFailure("Plz provide an email")
            return
        }
        if (password.isNullOrEmpty()){
            authListener?.onFailure("Plz add an password")
            return
        }

        if(password!=passwordConfirm){
            authListener?.onFailure("Password did not match")
            return
        }
        Coroutines.main {
            try {
                val response = repository.userSignUp(name!!,email!!,password!!)
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
            catch (e : NoInternetException){
                authListener?.onFailure(e.message.toString())
            }
        }
    }

    //click on resister or signup text
    fun register(view: View){
        Intent(view.context,SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun logIn(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

}