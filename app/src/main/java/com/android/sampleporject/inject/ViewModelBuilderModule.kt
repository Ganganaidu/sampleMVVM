package com.android.sampleporject.inject

import androidx.lifecycle.ViewModel
import com.android.sampleporject.base.viewmodel.BaseScheduler
import com.android.sampleporject.base.viewmodel.Schedulers
import com.android.sampleporject.base.viewmodel.ViewModelKey
import com.android.sampleporject.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}