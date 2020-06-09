package com.williamxyshi.njsandroid.fragments

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

class DetailFragment: Fragment() {

    private lateinit var vm: MainActivityViewModel

    private lateinit var posterImage: ImageView
    private lateinit var movieDescription: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false) as ViewGroup

        posterImage = rootView.findViewById(R.id.moviePoster)
        movieDescription = rootView.findViewById(R.id.movieDescription)


        initialize()

        Glide.with(this).load(vm.currentMovieDetail.value?.Poster).into(posterImage)
        movieDescription.text = vm.currentMovieDetail.value?.Plot



        return rootView
    }

    private fun initialize(){
        vm = ViewModelProviders.of(activity?:return).get(MainActivityViewModel::class.java)






    }

    companion object{
        const val TAG = "DetailFragment"
    }
}