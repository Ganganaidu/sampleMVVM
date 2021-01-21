package com.android.sampleporject.base.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.android.sampleporject.base.viewmodel.AppViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBuilder {
    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory):
            ViewModelProvider.Factory
}
