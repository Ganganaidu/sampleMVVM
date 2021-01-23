package com.android.sampleporject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.sampleporject.apiservice.ApiService
import com.android.sampleporject.base.viewmodel.Schedulers
import com.android.sampleporject.model.SearchResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private var apiService: ApiService,
    var scheduler: Schedulers
) : ViewModel() {
    private val disposable = CompositeDisposable()

    val searchLiveData = MutableLiveData<SearchResponse>()
    val loadingLiveObservable = MutableLiveData<Boolean>()
    val subject = PublishSubject.create<String>()

    fun doSearch() {
        disposable.add(subject
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter { text -> text.isNotBlank() && text.length >= 3 }
            .map { text -> text.toLowerCase(Locale.getDefault()).trim() }
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.mainThread())
            .subscribe {
                searchDataInServer(it)
            })
    }

    fun searchDataInServer(searchTag: String) {
        loadingLiveObservable.value = true
        disposable.add(
            apiService.searchData(searchTag)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.mainThread())
                .subscribe({
                    loadingLiveObservable.value = false
                    searchLiveData.value = it
                }, {
                    loadingLiveObservable.value = false
                    searchLiveData.value = null
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}