package com.williamxyshi.njsandroid.models.retrofitmodels

object UserModel {

    data class SignUpInfo(val username: String, val email: String, val password: String)

    data class Result(val username: String, val email: String)




}