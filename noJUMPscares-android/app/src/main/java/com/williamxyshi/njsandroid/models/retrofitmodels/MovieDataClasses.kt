package com.williamxyshi.njsandroid.models.retrofitmodels

/**
 * data classes used for node server
 */
object MovieDataClasses {

    data class MovieInfo(val name:String, val length: String)

    data class ServerMovieResult(val name: String, val length: Int, val posts: ArrayList<MoviePost> )

    data class MoviePost(val time: String, val majorscare: String, val description: String)
}