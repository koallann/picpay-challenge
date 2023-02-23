package com.picpay.desafio.android

import com.picpay.desafio.android.user.di.UserComponent
import dagger.Component
import dagger.Module

@Module(subcomponents = [])
class PicPaySubcomponentModule

@Component(modules = [PicPaySubcomponentModule::class])
interface PicPayApplicationComponent {
    fun userComponent(): UserComponent.Factory
}

interface ApplicationComponent {
    val appComponent: PicPayApplicationComponent
}
