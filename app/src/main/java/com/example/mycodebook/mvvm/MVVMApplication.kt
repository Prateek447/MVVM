package com.example.mycodebook.mvvm

import android.app.Application
import com.example.mycodebook.mvvm.data.db.AppDataBase
import com.example.mycodebook.mvvm.data.network.MyApi
import com.example.mycodebook.mvvm.data.network.NetworkConnnectionInterceptor
import com.example.mycodebook.mvvm.data.preferences.PreferenceProvider
import com.example.mycodebook.mvvm.data.repository.QuoteRepository
import com.example.mycodebook.mvvm.data.repository.UserRepository
import com.example.mycodebook.mvvm.util.AuthViewModelFactory
import com.example.mycodebook.mvvm.util.home.profile.ProfileViewModelFactory
import com.example.mycodebook.mvvm.util.home.quotes.QuoteViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


/*
* This class will instantiate or execute before all the other classes because it is Application() class
* */
class MVVMApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        //Here we pass instance() because NetworkConnectionInterceptor needs context
        bind() from singleton {NetworkConnnectionInterceptor(instance())}
        //In the following binding we pass instance() because all the objects need
        //instances of other which is present and instance() function will automatically
        //give the instance of object which is required
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDataBase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind()  from singleton { UserRepository(instance(),instance()) }
        bind()  from singleton { QuoteRepository(instance(),instance(),instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuoteViewModelFactory(instance()) }
    }
}