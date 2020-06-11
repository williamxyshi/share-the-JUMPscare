package com.williamxyshi.njsandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.adapters.FeaturedMoviesAdapter
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel
import android.R as androidR
import android.util.DisplayMetrics
import android.R.string.no
import android.R.attr.name
import android.util.Log
import com.williamxyshi.njsandroid.adapters.HomePageMovieAdapter
import com.williamxyshi.njsandroid.utils.WebServerAccessObject


class HomeFragment: Fragment() {

    private lateinit var vm: MainActivityViewModel


    private lateinit var  featuredMoviesRecyclerView: RecyclerView
    private lateinit var featuredMoviesAdapter: FeaturedMoviesAdapter

    private lateinit var mostScaredRecyclerView: RecyclerView
    private lateinit var mostScaredAdapter: HomePageMovieAdapter

    private lateinit var mostLikedRecyclerView: RecyclerView
    private lateinit var mostLikedAdapter: HomePageMovieAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false) as ViewGroup
        initialize()

        featuredMoviesRecyclerView = rootView.findViewById(R.id.featuredMovieRecyclerView)
        featuredMoviesAdapter = FeaturedMoviesAdapter(vm, context?:return null)
        featuredMoviesRecyclerView.adapter = featuredMoviesAdapter

        val snapHelper = object : LinearSnapHelper() {
            override fun findTargetSnapPosition(
                layoutManager: RecyclerView.LayoutManager?,
                velocityX: Int,
                velocityY: Int
            ): Int {
                val centerView = findSnapView(layoutManager!!) ?: return RecyclerView.NO_POSITION

                val position = layoutManager.getPosition(centerView)
                var targetPosition = -1
                if (layoutManager.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1
                    } else {
                        targetPosition = position + 1
                    }
                }

                if (layoutManager.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1
                    } else {
                        targetPosition = position + 1
                    }
                }

                val firstItem = 0
                val lastItem = layoutManager.itemCount - 1
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem))
                return targetPosition
            }
        }

        snapHelper.attachToRecyclerView(featuredMoviesRecyclerView)

        val displayMetrics = this.getResources().displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density


        featuredMoviesRecyclerView.setPadding((dpWidth/2).toInt() + 100, 0, (dpWidth/2).toInt() + 100, 0)
        featuredMoviesRecyclerView.layoutManager= LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        mostScaredRecyclerView = rootView.findViewById(R.id.scariestRecyclerView)
        mostScaredAdapter = HomePageMovieAdapter(context?:return null, vm, false)
        mostScaredRecyclerView.layoutManager =  LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mostScaredRecyclerView.adapter = mostScaredAdapter


        mostLikedRecyclerView = rootView.findViewById(R.id.mostLikesRecyclerView)
        mostLikedAdapter = HomePageMovieAdapter(context?:return null, vm, true)
        mostLikedRecyclerView.layoutManager =  LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mostLikedRecyclerView.adapter = mostLikedAdapter


        return rootView
    }

    private fun initialize(){
        vm = ViewModelProviders.of(activity?:return).get(MainActivityViewModel::class.java)

        vm.frontPage.observe(this, Observer {

            Log.d(TAG, "sizes: ${vm.frontPage.value?.MostLiked}")

            featuredMoviesRecyclerView.adapter?.notifyDataSetChanged()
            mostScaredRecyclerView.adapter?.notifyDataSetChanged()
            mostLikedRecyclerView.adapter?.notifyDataSetChanged()
        })

    }

    override fun onResume() {
        super.onResume()
        WebServerAccessObject.getFrontPage(vm)
        vm.currentPage.value = MainActivityViewModel.HOME_PAGE
    }


    companion object{
        private const val TAG = "HomeFragment"
    }
}