package com.example.mycodebook.mvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mycodebook.mvvm.R
import com.example.mycodebook.mvvm.databinding.ActivityLoginBinding
import com.example.mycodebook.mvvm.ui.home.HomeActivity
import com.example.mycodebook.mvvm.util.snackbar
import com.example.mycodebook.mvvm.util.toast
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware {

     private lateinit var  binding : ActivityLoginBinding
     private lateinit var  viewModel :  AuthViewModel
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel =   ViewModelProvider(this,factory).get(AuthViewModel::class.java)


        // to check the user is already loggedIn or not if already loggedIn
        // open the HomeAcitviy not logIn Activity
        viewModel.getLoggedInUser().observe(this, Observer {
            user ->

            if(user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })
        binding.buttonSignIn.setOnClickListener {
            logInUser()
        }

        binding.textViewSignUp.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }

    private fun logInUser() {
        val email =  binding.editTextEmail.text.toString().trim()
        val password =  binding.editTextPassword.text.toString().trim()

        lifecycleScope.launch {
            try {
                val authResponse  =  viewModel.userLogin(email, password)
                if (authResponse.user != null){
                    viewModel.saveLoggedInUser(authResponse.user)
                }
                else{
                    binding.rootLayout.snackbar(authResponse.message!!)
                }
            }
            catch (e: Exception){
                toast(e.message.toString())
            }
        }
    }


}