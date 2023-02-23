package com.picpay.desafio.android.common.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.picpay.desafio.android.common.database.AppDatabase
import com.picpay.desafio.android.common.database.DATABASE_NAME
import com.picpay.desafio.android.common.network.InternetConnectivity
import com.picpay.desafio.android.common.network.InternetConnectivityProxy
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [CoreModule.BindsModule::class])
class CoreModule {

    @Provides
    fun provideConnectivityManager(application: Application): ConnectivityManager {
        return application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            DATABASE_NAME,
        ).build()
    }

    @Module
    interface BindsModule {
        @Binds
        fun bindInternetConnectivity(proxy: InternetConnectivityProxy): InternetConnectivity
    }
}
