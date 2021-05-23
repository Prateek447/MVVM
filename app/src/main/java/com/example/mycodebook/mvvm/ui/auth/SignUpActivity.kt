package com.example.mycodebook.mvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mycodebook.mvvm.R
import com.example.mycodebook.mvvm.databinding.ActivitySignUpBinding
import com.example.mycodebook.mvvm.ui.home.HomeActivity
import com.example.mycodebook.mvvm.util.toast
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), KodeinAware {


    private lateinit var  binding : ActivitySignUpBinding

    private lateinit var viewModel: AuthViewModel

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        viewModel =  ViewModelProvider(this,factory).get(AuthViewModel::class.java)

        viewModel.getLoggedInUser().observe(this, Observer {
                user ->
            if(user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })

        binding.buttonSignUp.setOnClickListener {
            userSignUp()
        }


        binding.textViewLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    private fun userSignUp() {
        val name  = binding.editTextName.text.toString().trim()
        val email =  binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val confirmPassword = binding.editTextPasswordConfirm.text.toString().trim()


        // TODO: 23-May-21 You can check validation of inputs here

        lifecycleScope.launch {
            try {
                val authResponse  =  viewModel.userSignnUp(name,email, password)
                if (authResponse.user != null){
                    viewModel.saveLoggedInUser(authResponse.user)
                }
                else{
                    binding.root.snackbar(authResponse.message!!)
                }
            }
            catch (e: Exception){
                toast(e.message.toString())
            }
        }
    }


}