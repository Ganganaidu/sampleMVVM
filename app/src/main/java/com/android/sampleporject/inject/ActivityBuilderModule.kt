package com.android.sampleporject.inject

import com.android.sampleporject.base.viewmodel.ViewModelBuilder
import com.android.sampleporject.view.MainActivity
import com.android.sampleporject.view.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelBuilder::class])
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}