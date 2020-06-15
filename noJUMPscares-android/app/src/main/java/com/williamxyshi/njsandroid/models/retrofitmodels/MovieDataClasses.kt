package com.williamxyshi.njsandroid.models.retrofitmodels

/**
 * data classes used for node server
 */
object MovieDataClasses {

    data class MovieInfo(val name:String, val length: String, val posterurl: String)

    data class PostInfo(val name: String, val posttime: String, val majorscare: String, val description: String )

    data class VoteInfo(val name: String, val posttime: String, val description: String, val action: String )

    data class DeleteInfo(val name: String, val posttime: String, val posteremail: String)

    data class ServerMovieResult(val name: String, val length: String, val posts: ArrayList<MoviePost>, val posterurl: String, val pageviews: Int )

    data class MoviePost(val time: String, val majorscare: String, val description: String,  val upvotedBy: ArrayList<String>,  val downvotedBy: ArrayList<String>, val useremail: String)

    data class FrontPage(val FeaturedMovies: ArrayList<ServerMovieResult>, val MostScares: ArrayList<ServerMovieResult>, val MostLiked: ArrayList<ServerMovieResult> )
}