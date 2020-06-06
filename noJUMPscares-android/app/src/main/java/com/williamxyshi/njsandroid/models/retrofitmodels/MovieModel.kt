package com.williamxyshi.njsandroid.models.retrofitmodels

object MovieModel {
    data class  SearchArrayResult( val Search: ArrayList<SearchResult>)

    data class SearchResult( val Title: String, val Type: String, val Year: String, val Poster: String)

}