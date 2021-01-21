package com.android.sampleporject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.sampleporject.apiservice.ApiService
import com.android.sampleporject.model.SearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private var apiService: ApiService) : ViewModel() {
    val searchLiveData = MutableLiveData<SearchResponse>()
    private val disposable = CompositeDisposable()

    fun getSearchData(searchTag: String) {
        disposable.add(
            apiService.searchData(searchTag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    searchLiveData.value = it
                }, { searchLiveData.value = null })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}