package com.williamxyshi.njsandroid.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.williamxyshi.njsandroid.models.User


class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    //current fragment the activity is on
    var currentPage: MutableLiveData<Int> = MutableLiveData()


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

        const val HOME_PAGE = 0
        const val SEARCH_PAGE = 1

        /**
         * user page includes loginfragment and loggedin fragment
         */
        const val USER_PAGE = 2

        const val DETAILS_PAGE = 3

    }


}