package com.williamxyshi.njsandroid.models.retrofitmodels

/**
 * data classes used for node server
 */
object MovieDataClasses {

    data class MovieInfo(val name:String, val length: String, val posterurl: String)

    data class PostInfo(val name: String, val posttime: String, val majorscare: String, val description: String )

    data class ServerMovieResult(val name: String, val length: String, val posts: ArrayList<MoviePost>, val posterurl: String )

    data class MoviePost(val time: String, val majorscare: String, val description: String,  val upvotedBy: ArrayList<String>)

    data class FrontPage(val FeaturedMovies: ArrayList<ServerMovieResult>, val MostScares: ArrayList<ServerMovieResult>, val MostLiked: ArrayList<ServerMovieResult> )
}