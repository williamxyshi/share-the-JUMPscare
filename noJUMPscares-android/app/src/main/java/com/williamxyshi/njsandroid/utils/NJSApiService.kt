package com.williamxyshi.njsandroid.utils

import com.williamxyshi.njsandroid.models.TokenModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface NJSApiService {

    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("email") email: String, @Field("password") password: String ):
            Observable<TokenModel.Result>







    companion object {

        const val TAG = "NJSApiService"


        //api endpoint URL -> change to AWS endpoint when server is turned on
        private const val awsURL = "http:/flask-env.jjxav9tgia.us-east-2.elasticbeanstalk.com/"
        private const val localURL = "http:/192.168.0.27:3000/"


        fun create(): NJSApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(localURL)
                .build()

            return retrofit.create(NJSApiService::class.java)
        }
    }
}