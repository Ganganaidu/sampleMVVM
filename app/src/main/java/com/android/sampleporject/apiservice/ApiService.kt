package com.android.sampleporject.apiservice

import com.android.sampleporject.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("photos_public.gne?nojsoncallback=1&tagmode=any&format=json")
    fun searchData(@Query("tags") searchTag: String): Observable<SearchResponse>
}