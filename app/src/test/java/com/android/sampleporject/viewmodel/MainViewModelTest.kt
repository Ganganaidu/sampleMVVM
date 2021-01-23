package com.android.sampleporject.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.sampleporject.apiservice.ApiService
import com.android.sampleporject.base.viewmodel.Schedulers
import com.android.sampleporject.model.Item
import com.android.sampleporject.model.SearchResponse
import io.reactivex.Observable
import io.reactivex.Scheduler
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private lateinit var schedulers: Schedulers
    private lateinit var searchResponse: Observable<SearchResponse>

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        schedulers = object : Schedulers {
            override fun io(): Scheduler {
                return io.reactivex.schedulers.Schedulers.trampoline()
            }

            override fun computation(): Scheduler {
                return io.reactivex.schedulers.Schedulers.trampoline()
            }

            override fun mainThread(): Scheduler {
                return io.reactivex.schedulers.Schedulers.trampoline()
            }
        }
        viewModel = MainViewModel(apiService, schedulers)
    }

    @Test
    fun fetchRetroInfoTest_success() {
        val itemModel = Item(
                "DSCF6603", "https://www.flickr.com/photos/tags/car/", null,
                "2020-04-10T13:24:13-08:00",
                "posted a photo", "2021-01-21T18:45:02Z",
                "nobody", "96016676", "tromsoya troms"
        )
        val itemList = ArrayList<Item>()
        itemList.add(itemModel)

        val searchResponseModel = SearchResponse(
                "Recent Uploads tags", "https://www.flickr.com/photos/tags/car/",
                "", null, null, itemList
        )

        searchResponse = Observable.just(searchResponseModel)
        Mockito.`when`(apiService.searchData("car")).thenReturn(searchResponse)

        viewModel.searchDataInServer("car")
        Assert.assertEquals(1, viewModel.searchLiveData.value?.items?.size)
    }

    @Test
    fun fetchRetroInfoTest_Failure_Scenario() {
        searchResponse = Observable.error(Throwable())
        Mockito.`when`(apiService.searchData("")).thenReturn(searchResponse)
        viewModel.searchDataInServer("")
        Assert.assertNull(viewModel.searchLiveData.value)
    }
}