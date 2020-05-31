package com.williamxyshi.njsandroid.utils

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NJSApiService {



    companion object {

        //api endpoint URL -> change to AWS endpoint when server is turned on
        private const val awsURL = "http:/flask-env.jjxav9tgia.us-east-2.elasticbeanstalk.com/"
        private const val localURL = "http:/192.168.0.27:5000/"


//        fun create(): WizAutoChesApiService {
//            val retrofit = Retrofit.Builder()
//                .addCallAdapterFactory(
//                    RxJava2CallAdapterFactory.create())
//                .addConverterFactory(
//                    GsonConverterFactory.create())
//                .baseUrl(awsURL)
//                .build()
//
//            return retrofit.create(WizAutoChesApiService::class.java)
//        }
    }
}