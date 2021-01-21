package com.android.sampleporject.inject

import android.app.Application
import android.content.Context
import com.android.sampleporject.MainApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApplication(application: MainApplication): Application = application

    @Provides
    fun provideApplicationContext(contex: MainApplication): Context = contex
}