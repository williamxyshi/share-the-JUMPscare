package com.williamxyshi.njsandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

class DetailFragment: Fragment() {

    private lateinit var vm: MainActivityViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initialize()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initialize(){

        vm = ViewModelProviders.of(activity?:return).get(MainActivityViewModel::class.java)




    }

    companion object{
        const val TAG = "DetailFragment"
    }
}