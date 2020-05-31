package com.williamxyshi.njsandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.williamxyshi.njsandroid.fragments.LoginFragment
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    val loginFragment: LoginFragment = LoginFragment()

    private lateinit var vm: MainActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initialize()
        supportFragmentManager.beginTransaction().add(R.id.fragmentView, loginFragment).commit()
    }







    private fun initialize(){
        vm = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

    }

    companion object{
        const val TAG = "DashboardFragment"
    }
}
