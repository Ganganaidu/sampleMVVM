package com.android.sampleporject.inject

import com.android.sampleporject.MainApplication
import com.android.sampleporject.base.viewmodel.ViewModelBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelBuilderModule::class,
        ViewModelBuilder::class,
        ActivityBuilderModule::class,
        NetworkModule::class]
)

interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}