package com.williamxyshi.njsandroid.utils

import com.williamxyshi.njsandroid.models.TokenModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface NJSApiService {

    /**
     * FIX ME: POST REQUEST FORMATI @FIELD is wrong
     *
     * https://stackoverflow.com/questions/30180957/send-post-request-with-params-using-retrofit
     *
     */


    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field( value = "email", encoded = true) email: String, @Field("password") password: String ):
            Observable<TokenModel.Result>







    companion object {

        const val TAG = "NJSApiService"


        //api endpoint URL -> change to AWS endpoint when server is turned on
        private const val awsURL = "http:/flask-env.jjxav9tgia.us-east-2.elasticbeanstalk.com/"
        private const val localURL = "http:/192.168.1.12:4000/"


//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();




        fun create(): NJSApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(localURL)
                .client(client)
                .build()

            return retrofit.create(NJSApiService::class.java)
        }
    }


}