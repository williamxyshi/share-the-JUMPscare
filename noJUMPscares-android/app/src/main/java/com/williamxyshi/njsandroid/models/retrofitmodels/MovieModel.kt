package com.williamxyshi.njsandroid.models.retrofitmodels

/**
 *
 * movie models for OMDB api
 *
 */
object MovieModel {
    data class  SearchArrayResult( val Search: ArrayList<SearchResult>)

    data class SearchResult( val Title: String, val Type: String, val Year: String, val Poster: String)


    data class MovieResult( val Title: String,val Type: String, val Year: String, val Poster: String , val Plot: String, val imdbRating: String,  val Genre: String, val Actors: String, val Runtime: String)


}