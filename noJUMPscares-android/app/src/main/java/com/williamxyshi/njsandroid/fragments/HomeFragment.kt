package com.williamxyshi.njsandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

class HomeFragment: Fragment() {

    private lateinit var vm: MainActivityViewModel


    private lateinit var  featuredMoviesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false) as ViewGroup

        featuredMoviesRecyclerView = rootView.findViewById(R.id.featuredMovieRecyclerView)
        featuredMoviesRecyclerView .layoutManager= LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        initialize()





        return rootView
    }

    private fun initialize(){
        vm = ViewModelProviders.of(activity?:return).get(MainActivityViewModel::class.java)


    }


    companion object{
        private const val TAG = "HomeFragment"
    }
}