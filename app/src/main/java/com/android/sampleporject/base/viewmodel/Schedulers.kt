package com.android.sampleporject.base.viewmodel

import io.reactivex.Scheduler

interface Schedulers {

    fun io(): Scheduler

    fun computation(): Scheduler

    fun mainThread(): Scheduler
}