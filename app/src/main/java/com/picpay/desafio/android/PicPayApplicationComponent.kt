package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.common.di.CoreModule
import com.picpay.desafio.android.user.di.UserComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Module(
    subcomponents = [
        UserComponent::class,
    ]
)
class PicPaySubcomponentModule

@Singleton
@Component(
    modules = [
        PicPaySubcomponentModule::class,
        CoreModule::class,
    ]
)
interface PicPayApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): PicPayApplicationComponent
    }

    val userComponent: UserComponent.Factory
}

interface ApplicationComponent {
    val appComponent: PicPayApplicationComponent
}
