package com.williamxyshi.njsandroid.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.williamxyshi.njsandroid.models.User
import com.williamxyshi.njsandroid.models.retrofitmodels.MovieDataClasses
import com.williamxyshi.njsandroid.models.retrofitmodels.MovieModel


class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    //current fragment the activity is on
    var currentPage: MutableLiveData<Int> = MutableLiveData()

    /**
     * internal user auth token  server sends us to get user profile
     */
    var userToken: MutableLiveData<String> = MutableLiveData()

    /**
     * the user profile
     */
    var user: MutableLiveData<User> = MutableLiveData()

    /**
     * error message, if an error message gets set here, we display a toast
     */
    var failedToLogIn: MutableLiveData<String> = MutableLiveData()

    /**
     * movies to display from search results
     */
    var searchPageResults: MutableLiveData<MovieModel.SearchArrayResult> = MutableLiveData()

    /**
     * movies to display from featured movies
     */
    var frontPage: MutableLiveData<MovieDataClasses.FrontPage> = MutableLiveData()


    /**
     * current movie to display for detail fragment from omdb api
     */
    var currentMovieDetail: MutableLiveData<MovieModel.MovieResult> = MutableLiveData()

    /**
     * current movie to display from node server (post data etc)
     */
    var currentMovieDetailWebServer: MutableLiveData<MovieDataClasses.ServerMovieResult> = MutableLiveData()



    var newPostClicked: MutableLiveData<Boolean> = MutableLiveData()


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