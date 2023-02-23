package com.picpay.desafio.android.user.di

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.common.di.ViewModelKey
import com.picpay.desafio.android.user.presentation.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [UserNetworkModule::class])
interface UserModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    fun userViewModel(viewModel: UserViewModel): ViewModel
}
