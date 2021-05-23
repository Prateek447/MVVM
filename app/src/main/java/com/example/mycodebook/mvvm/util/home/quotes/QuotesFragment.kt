package com.example.mycodebook.mvvm.util.home.quotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycodebook.mvvm.R
import com.example.mycodebook.mvvm.util.ApiExceptions
import com.example.mycodebook.mvvm.util.Coroutines
import com.example.mycodebook.mvvm.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment() , KodeinAware{

    override val kodein by kodein()
    private val factory : QuoteViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,factory).get(QuotesViewModel::class.java)
        Coroutines.main {
            try {

                val quotes =  viewModel.quotes.await()
                quotes.observe(this, Observer {
                    context?.toast(it.size.toString())
                })
            }
            catch (e:ApiExceptions){
                context?.toast(e.message.toString())
            }
        }
    }

}