package com.example.mycodebook.mvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mycodebook.mvvm.R
import com.example.mycodebook.mvvm.data.db.entites.User
import com.example.mycodebook.mvvm.databinding.ActivityLoginBinding
import com.example.mycodebook.mvvm.util.AuthViewModelFactory
import com.example.mycodebook.mvvm.util.hide
import com.example.mycodebook.mvvm.util.home.HomeActivity
import com.example.mycodebook.mvvm.util.show
import com.example.mycodebook.mvvm.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {


    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel =  ViewModelProviders.of(this,factory).get(ActivityViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this

        // to check the user is already loggedIn or not if already loggedIn open the HomeAcitviy not logIn Activity
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

    override fun onStarted() {
           progress_bar.show()
    }

    override fun onSuccess(user: User?) {
            progress_bar.hide()
            root_layout.snackbar("${user?.name} is log in")

    }

    override fun onFailure(message: String){
        progress_bar.hide()
        root_layout.snackbar(message)
    }


}