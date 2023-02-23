package com.picpay.desafio.android

import com.picpay.desafio.android.user.di.UserComponent
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
    ]
)
interface PicPayApplicationComponent {
    val userComponent: UserComponent.Factory
}

interface ApplicationComponent {
    val appComponent: PicPayApplicationComponent
}
