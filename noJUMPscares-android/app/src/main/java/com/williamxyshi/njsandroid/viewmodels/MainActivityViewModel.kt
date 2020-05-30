package com.williamxyshi.njsandroid.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    //current fragment the activity is on
    var currentActionPage: MutableLiveData<Int> = MutableLiveData()




    companion object {
        const val TAG = "MainActivityViewModel"

    }


}