package com.example.mycodebook.mvvm.util.home.profile

import androidx.lifecycle.ViewModel
import com.example.mycodebook.mvvm.data.repository.MyRepository

class ProfileViewModel(repository: MyRepository) : ViewModel() {

  val user = repository.getUser()

}