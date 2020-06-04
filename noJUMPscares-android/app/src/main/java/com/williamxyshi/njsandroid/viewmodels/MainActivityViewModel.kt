package com.williamxyshi.njsandroid.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.williamxyshi.njsandroid.models.User


class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    //current fragment the activity is on
    var currentActionPage: MutableLiveData<Int> = MutableLiveData()


    var userToken: MutableLiveData<String> = MutableLiveData()

    var failedToLogIn: MutableLiveData<String> = MutableLiveData()

    var user: MutableLiveData<User> = MutableLiveData()


    var userPassword:String = ""
    var userEmail:String  = ""

    fun setEmailPassword(email: String, password: String){
        userEmail = email
        userPassword = password
    }



    companion object {
        const val TAG = "MainActivityViewModel"

    }


}