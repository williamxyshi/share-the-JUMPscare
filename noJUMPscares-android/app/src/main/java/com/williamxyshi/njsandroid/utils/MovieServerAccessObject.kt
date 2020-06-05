package com.williamxyshi.njsandroid.utils

import android.app.Application
import android.content.Context
import android.util.Log
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MovieServerAccessObject {

    private val ombdbApiService by lazy {
        OMDbApiService.create()
    }


    val apiKey = "d0e1a16f"

    fun searchMovie(movieName: String, vm: MainActivityViewModel){

        ombdbApiService.searchMovie( apiKey, movieName ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                        Log.d(TAG, "result for server: ${result}" )
                },
                { error ->
                    showResult(error.message ?: "ERROR")
                }
            )

    }


    private fun showResult(string: String){

        Log.d(TAG, "result from server: ${string} ")

    }

    private const val TAG = "MovieServerAccessObject"


}