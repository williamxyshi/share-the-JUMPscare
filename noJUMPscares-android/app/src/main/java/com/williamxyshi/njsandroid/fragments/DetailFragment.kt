package com.williamxyshi.njsandroid.fragments

import android.media.Image
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.adapters.HomePageMovieAdapter
import com.williamxyshi.njsandroid.adapters.MoviePostAdapter
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel
import android.widget.LinearLayout
import com.williamxyshi.njsandroid.models.retrofitmodels.MovieDataClasses
import com.williamxyshi.njsandroid.utils.WebServerAccessObject


class DetailFragment: Fragment() {

    private lateinit var vm: MainActivityViewModel

    private lateinit var posterImage: ImageView
    private lateinit var movieDescription: TextView
    private lateinit var jumpScaresCount: TextView
    private lateinit var genre: TextView
    private lateinit var views: TextView

    private lateinit var postRecyclerView: RecyclerView
    private lateinit var postAdapter: MoviePostAdapter
    private lateinit var newPost: FrameLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false) as ViewGroup

        posterImage = rootView.findViewById(R.id.moviePoster)
        movieDescription = rootView.findViewById(R.id.movieDescription)
        jumpScaresCount = rootView.findViewById(R.id.jumpScaresCounter)
        views = rootView.findViewById(R.id.viewsCount)

        genre = rootView.findViewById(R.id.movieGenre)

        newPost = rootView.findViewById(R.id.newButton)


        initialize()

        Glide.with(this).load(vm.currentMovieDetail.value?.Poster).into(posterImage)
        movieDescription.text = vm.currentMovieDetail.value?.Plot

        postRecyclerView = rootView.findViewById(R.id.postsRecyclerView)

        postAdapter = MoviePostAdapter(context?:return null, vm)
        postRecyclerView.layoutManager =  LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        postRecyclerView.adapter = postAdapter


        genre.text = "genre: " + vm.currentMovieDetail.value?.Genre

        newPost.setOnClickListener {
            onButtonShowPopupWindowClick(rootView)
        }


        return rootView
    }

    private fun initialize(){
        vm = ViewModelProviders.of(activity?:return).get(MainActivityViewModel::class.java)

        vm.currentMovieDetailWebServer.observe(this, Observer{

            postRecyclerView.adapter?.notifyDataSetChanged()
            jumpScaresCount.text = "Recorded Jump Scares: " + it.posts.size.toString()
            views.text = ": " + vm.currentMovieDetailWebServer.value?.pageviews

        })




    }


    fun onButtonShowPopupWindowClick(view: View) {

        // inflate the layout of the popup window
        val popupView = layoutInflater.inflate(R.layout.popup_window, null)

        // create the popup window
        val width = LinearLayout.LayoutParams.WRAP_CONTENT;
        var height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = true // lets taps outside the popup also dismiss it
        val popupWindow = PopupWindow(popupView, width, height, focusable)

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

        val mins = popupView.findViewById<EditText>(R.id.minutesPicker)
        val secs = popupView.findViewById<EditText>(R.id.secondsPicker)
        val desc = popupView.findViewById<EditText>(R.id.postDescription)
        val major = popupView.findViewById<CheckBox>(R.id.majorscareCheckbox)
        val postButton = popupView.findViewById<Button>(R.id.postButton)



        postButton.setOnClickListener {
            val time: String = mins.text.toString() + ":" + secs.text.toString()
            if(vm.userToken.value != null){
                WebServerAccessObject.addPost(MovieDataClasses.PostInfo(vm.currentMovieDetailWebServer.value?.name?:return@setOnClickListener, time, major.isChecked.toString(), desc.text.toString()), vm)
                popupWindow.dismiss()
            } else {

                vm.errorMessage.value = "please log in to post"
            }
            Log.d(TAG, "time: ${mins.text}:${secs.text }, desc: ${desc.text}, major: ${major.isChecked}")


        }
    }

    companion object{
        const val TAG = "DetailFragment"
    }
}