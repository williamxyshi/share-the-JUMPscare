package com.williamxyshi.njsandroid.utils

import android.util.Log
import com.williamxyshi.njsandroid.models.User
import com.williamxyshi.njsandroid.models.retrofitmodels.LoginInfo
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object WebServerAccessObject {

    private val njsApiService by lazy {
        NJSApiService.create()
    }

    fun loginCall(vm: MainActivityViewModel){
        val info = LoginInfo(
            vm.userEmail,
            vm.userPassword
        )

        njsApiService.login( info ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showResult(result.token)
                    vm.userToken.value = result.token.toString()},
                { error ->
                    vm.failedToLogIn.value = error.message
                    showResult(error.message?:"ERROR") }
            )
    }

    fun getUserCall(vm: MainActivityViewModel){

        njsApiService.getUser(vm.userToken.value?:return).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val user: User = User(it.username, it.email)
                Log.d(TAG,"user: ${it.username}, ${it.email}")
                vm.user.value = user
            },
            {error ->
                showResult(error.message?:"ERROR")

            }

            )

    }

    private fun showResult(string: String){
        Log.d(TAG, "result from server: ${string} ")

    }


    private const val TAG = "WebServerAccessObject"

}