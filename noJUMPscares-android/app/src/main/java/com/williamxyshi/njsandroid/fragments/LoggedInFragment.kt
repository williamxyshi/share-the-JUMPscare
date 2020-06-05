package com.williamxyshi.njsandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

class LoggedInFragment : Fragment() {

    private lateinit var vm: MainActivityViewModel
    private lateinit var logOutButton: Button
    private lateinit var welcomeUser: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_logged_in, container, false) as ViewGroup

        logOutButton = rootView.findViewById(R.id.logOutButton)
        welcomeUser = rootView.findViewById(R.id.welcomeUser)

        setUpVM()

        initialize()


        return rootView
    }

    private fun initialize(){
        logOutButton.setOnClickListener {

            vm.userToken.value = null
            vm.user.value = null

        }

    }

    private fun setUpVM(){

        vm = ViewModelProviders.of(activity?:return).get(MainActivityViewModel::class.java)
    }

}