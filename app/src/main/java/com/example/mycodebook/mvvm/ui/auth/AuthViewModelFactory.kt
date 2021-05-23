package com.example.mycodebook.mvvm.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycodebook.mvvm.data.repository.UserRepository


/*
* we make this class because we want to send the context or repository to the viewmodel class but the default
* ViewModelProviders do not have any parameter to pass the context to the viewmodel
* so we make an ModelFactory which will give us an instance of ViewModel with the parameter which
* we want to pass to the viewModel class.
* */
@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}