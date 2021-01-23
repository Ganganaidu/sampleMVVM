package com.android.sampleporject.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


object AppConstants {
    //For now declared here, but it can move to gradle to load based on build flavors.
    const val BASE_URL = "https://api.flickr.com/services/feeds/"


    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}