package com.example.mycodebook.mvvm.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.mycodebook.mvvm.data.repository.QuoteRepository
import net.simplifiedcoding.mvvmsampleapp.util.lazyDeferred

class QuotesViewModel(quoteRepository: QuoteRepository) : ViewModel() {

    val quotes by lazyDeferred {
        quoteRepository.getQuotes()
    }

}