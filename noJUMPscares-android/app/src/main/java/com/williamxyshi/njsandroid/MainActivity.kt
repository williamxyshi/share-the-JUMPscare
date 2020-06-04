package com.williamxyshi.njsandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.williamxyshi.njsandroid.fragments.LoginFragment
import com.williamxyshi.njsandroid.utils.WebServerAccessObject
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val loginFragment: LoginFragment = LoginFragment()

    private lateinit var vm: MainActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initialize()
        setUpNavigationBar()

        supportFragmentManager.beginTransaction().add(R.id.fragmentView, loginFragment).commit()
    }







    private fun initialize(){
        vm = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        vm.userToken.observe(this, androidx.lifecycle.Observer {

            Log.d(LoginFragment.TAG, "calling get user call")
            WebServerAccessObject.getUserCall(vm)


        })
    }

    fun setUpNavigationBar(){
        navigationView.setOnNavigationItemSelectedListener {

            when(it.itemId){

                R.id.bottom_navigation_login-> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentView, loginFragment).commit()
                        addToBackStack(null)
                    }
                    true
                }

                else -> {

                    false
                }
            }

        }
    }

    companion object{
        const val TAG = "DashboardFragment"
    }
}
