package com.picpay.desafio.android.user.di

import com.picpay.desafio.android.common.di.ActivityScope
import com.picpay.desafio.android.common.viewmodel.ViewModelFactoryModule
import com.picpay.desafio.android.user.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        ViewModelFactoryModule::class,
        UserModule::class,
    ]
)
interface UserComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    fun inject(activity: MainActivity)
}
