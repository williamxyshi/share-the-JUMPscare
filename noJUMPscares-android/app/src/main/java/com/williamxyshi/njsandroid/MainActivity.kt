package com.williamxyshi.njsandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.williamxyshi.njsandroid.fragments.HomeFragment
import com.williamxyshi.njsandroid.fragments.LoggedInFragment
import com.williamxyshi.njsandroid.fragments.LoginFragment
import com.williamxyshi.njsandroid.utils.WebServerAccessObject
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val loginFragment: LoginFragment = LoginFragment()
    private val loggedinFragment: LoggedInFragment = LoggedInFragment()

    private val homeFragment: HomeFragment = HomeFragment()

    private lateinit var vm: MainActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initialize()
        setUpNavigationBar()

        supportFragmentManager.beginTransaction().add(R.id.fragmentView, homeFragment).commit()
    }







    private fun initialize(){
        vm = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        vm.userToken.observe(this, androidx.lifecycle.Observer {

            Log.d(LoginFragment.TAG, "calling get user call")
            WebServerAccessObject.getUserCall(vm)

        })

        vm.user.observe(this, Observer {
            if(it != null){
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragmentView, loggedinFragment).commit()
                    addToBackStack(null)
                }
            }

        })
    }

    private fun setUpNavigationBar(){
        navigationView.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.bottom_navigation_home-> {
                    vm.currentPage.value = MainActivityViewModel.HOME_PAGE
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentView, homeFragment).commit()
                        addToBackStack(null)
                    }
                    true
                }

                R.id.bottom_navigation_login-> {
                    vm.currentPage.value = MainActivityViewModel.USER_PAGE
                    if(vm.user.value == null) {
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.fragmentView, loginFragment).commit()
                            addToBackStack(null)
                        }
                    } else {
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.fragmentView, loggedinFragment).commit()
                            addToBackStack(null)
                        }
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
