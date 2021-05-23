package com.example.mycodebook.mvvm.util.home.profile

import androidx.lifecycle.ViewModel
import com.example.mycodebook.mvvm.data.repository.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {

  val user = repository.getUser()

}