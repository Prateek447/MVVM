package com.example.mycodebook.mvvm.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mycodebook.mvvm.R
import com.example.mycodebook.mvvm.databinding.ActivityLoginBinding
import com.example.mycodebook.mvvm.util.hide
import com.example.mycodebook.mvvm.util.show
import com.example.mycodebook.mvvm.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //setContentView(R.layout.activity_login)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel =  ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
           progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it)
        })
    }

    override fun onFailure(message: String){
        toast("failure")
    }
}