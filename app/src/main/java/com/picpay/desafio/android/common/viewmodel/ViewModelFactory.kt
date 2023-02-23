package com.picpay.desafio.android.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.common.di.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(clazz: Class<T>): T = viewModels[clazz]?.get() as T
}
