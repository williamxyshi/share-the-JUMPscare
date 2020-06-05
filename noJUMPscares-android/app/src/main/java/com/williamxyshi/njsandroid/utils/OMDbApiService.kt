package com.williamxyshi.njsandroid.utils

import com.williamxyshi.njsandroid.models.retrofitmodels.MovieModel
import com.williamxyshi.njsandroid.models.retrofitmodels.UserModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface OMDbApiService {


    @GET("/")
    fun searchMovie(@Query("apikey") apikey: String, @Query("s") s: String):
            Observable<MovieModel.SearchArrayResult>




    companion object {

        const val TAG = "OMDbApiService"


        //api endpoint URL -> change to AWS endpoint when server is turned on
        private const val url = "http://www.omdbapi.com/"



        fun create(): OMDbApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(url)
                .client(client)
                .build()

            return retrofit.create(OMDbApiService::class.java)
        }
    }
}