package com.williamxyshi.njsandroid.utils

import com.williamxyshi.njsandroid.models.retrofitmodels.LoginInfo
import com.williamxyshi.njsandroid.models.retrofitmodels.MovieDataClasses
import com.williamxyshi.njsandroid.models.retrofitmodels.TokenModel
import com.williamxyshi.njsandroid.models.retrofitmodels.UserModel
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableError
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

    @POST("/user/login")
    fun login(@Body loginInfo: LoginInfo):
            Observable<TokenModel.Result>

    @GET("/user/me")
    fun getUser(@Header("token") token: String):
            Observable<UserModel.Result>

    @POST("/movie/getmovie")
    fun getMovie(@Body movieinfo: MovieDataClasses.MovieInfo) :
            Observable<MovieDataClasses.ServerMovieResult>

    @GET("/movie/frontpage")
    fun getFrontPage():
            Observable<MovieDataClasses.FrontPage>

    @POST("/movie/addpost")
    fun addPost(@Header("token") token: String, @Body postInfo: MovieDataClasses.PostInfo) :
            Observable<MovieDataClasses.ServerMovieResult>


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