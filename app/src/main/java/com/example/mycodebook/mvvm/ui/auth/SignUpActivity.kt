package com.example.mycodebook.mvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mycodebook.mvvm.R
import com.example.mycodebook.mvvm.databinding.ActivitySignUpBinding
import com.example.mycodebook.mvvm.util.AuthViewModelFactory
import com.example.mycodebook.mvvm.util.home.HomeActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySignUpBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        val viewModel =  ViewModelProviders.of(this,factory).get(UserViewModel::class.java)

        viewModel.getLoggedInUser().observe(this, Observer {
                user ->

            if(user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })
    }



}