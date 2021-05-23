package com.example.mycodebook.mvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycodebook.mvvm.data.db.entites.Quotes
import com.example.mycodebook.mvvm.data.db.entites.User


@Database(
    entities = [User::class, Quotes::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getQuoteDao() : QuoteDao

    companion object {

        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "MyDatabase.db"
            ).build()
    }


}