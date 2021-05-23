package com.example.mycodebook.mvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycodebook.mvvm.data.db.AppDataBase
import com.example.mycodebook.mvvm.data.db.entites.Quotes
import com.example.mycodebook.mvvm.data.network.MyApi
import com.example.mycodebook.mvvm.data.network.SafeApiRequest
import com.example.mycodebook.mvvm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository(private val api: MyApi,

                      private val db: AppDataBase) : SafeApiRequest() {



      private val quotes =  MutableLiveData<List<Quotes>>()

       init {
           quotes.observeForever{
               saveQuotes(it)
           }
       }

    //get data from the local repository
     suspend fun getQuotes() : LiveData<List<Quotes>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }



    //get data from the network
    private suspend fun fetchQuotes(){
        if (isFetchNeeded()){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(): Boolean{
        return true
    }
    private fun saveQuotes(quotes: List<Quotes>) {
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }


}