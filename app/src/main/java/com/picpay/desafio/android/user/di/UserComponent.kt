package com.picpay.desafio.android.user.di

import com.picpay.desafio.android.user.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [UserNetworkModule::class])
interface UserComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    fun inject(activity: MainActivity)
}
