package com.williamxyshi.njsandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.williamxyshi.njsandroid.fragments.*
import com.williamxyshi.njsandroid.utils.WebServerAccessObject
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val loginFragment: LoginFragment = LoginFragment()
    private val loggedinFragment: LoggedInFragment = LoggedInFragment()

    private val homeFragment: HomeFragment = HomeFragment()

    private val searchFragment: SearchFragment = SearchFragment()
    private val detailFragment: DetailFragment = DetailFragment()


    private lateinit var vm: MainActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initialize()
        setUpNavigationBar()

        supportFragmentManager.beginTransaction().add(R.id.fragmentView, homeFragment).commit()
        vm.currentPage.value = MainActivityViewModel.HOME_PAGE

        /**
         * get front page movies
         */
    }







    private fun initialize(){
        vm = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        vm.userToken.observe(this, androidx.lifecycle.Observer {

            Log.d(LoginFragment.TAG, "calling get user call")
            WebServerAccessObject.getUserCall(vm)

        })

        vm.user.observe(this, Observer {

            if(it != null){
                /**
                 * user logged in
                 */
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragmentView, loggedinFragment).commit()
                    addToBackStack(null)
                }
            } else {
                /**
                 *  user logged out
                 */
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragmentView, loginFragment).commit()
                    addToBackStack(null)
                }
            }


        })

        vm.currentPage.observe(this, Observer {
            when(it){
                MainActivityViewModel.DETAILS_PAGE -> {
                    supportActionBar?.title = vm.currentMovieDetail.value?.Title
                }
                MainActivityViewModel.SEARCH_PAGE->{
                    supportActionBar?.title = "Search Movies"

                }
                MainActivityViewModel.USER_PAGE -> {
                    supportActionBar?.title = "User Profile"

                }
                MainActivityViewModel.HOME_PAGE -> {
                    supportActionBar?.title = "Share the Jumpscares"
                }




            }
        })


        vm.currentMovieDetail.observe(this, Observer {

            /**
             * get the post data from node server
             */
            WebServerAccessObject.getMovie(it.Title, it.Runtime, it.Poster, vm)


            vm.currentPage.value = MainActivityViewModel.DETAILS_PAGE
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentView, detailFragment).commit()
                addToBackStack(null)
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
                R.id.bottom_navigation_search -> {
                    vm.currentPage.value = MainActivityViewModel.SEARCH_PAGE

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentView, searchFragment).commit()
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
