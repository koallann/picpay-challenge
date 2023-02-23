package com.picpay.desafio.android

import android.app.Application

class PicPayApplication : Application(), ApplicationComponent {

    override val appComponent: PicPayApplicationComponent =
        DaggerPicPayApplicationComponent
            .builder()
            .application(this)
            .build()

}
