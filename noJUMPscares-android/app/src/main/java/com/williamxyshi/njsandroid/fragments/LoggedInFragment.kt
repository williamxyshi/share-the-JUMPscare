package com.williamxyshi.njsandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

class LoggedInFragment : Fragment() {

    private lateinit var vm: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_logged_in, container, false) as ViewGroup

        setUpVM()

        return rootView
    }


    fun setUpVM(){

        vm = ViewModelProviders.of(activity?:return).get(MainActivityViewModel::class.java)
    }

}