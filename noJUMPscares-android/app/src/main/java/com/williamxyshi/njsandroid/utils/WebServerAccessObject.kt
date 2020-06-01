package com.williamxyshi.njsandroid.utils

import android.util.Log
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object WebServerAccessObject {

    private val njsApiService by lazy {
        NJSApiService.create()
    }

    fun loginCall(vm: MainActivityViewModel){
        njsApiService.login(vm.userEmail, vm.userPassword).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showResult(result.token)
                    vm.userToken.value = result.token.toString()},
                { error -> showResult(error.message?:"ERROR") }
            )
    }

    fun showResult(string: String){
        Log.d(TAG, "result from server: ${string} ")

    }


    private const val TAG = "WebServerAccessObject"

}