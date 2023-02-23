package com.picpay.desafio.android.user.di

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.common.database.AppDatabase
import com.picpay.desafio.android.common.viewmodel.ViewModelKey
import com.picpay.desafio.android.user.data.local.UserDAO
import com.picpay.desafio.android.user.presentation.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [UserModule.BindsModule::class, UserNetworkModule::class])
class UserModule {
    @Provides
    fun provideUserDAO(database: AppDatabase): UserDAO = database.userDAO

    @Module
    interface BindsModule {
        @Binds
        @IntoMap
        @ViewModelKey(UserViewModel::class)
        fun userViewModel(viewModel: UserViewModel): ViewModel
    }
}
