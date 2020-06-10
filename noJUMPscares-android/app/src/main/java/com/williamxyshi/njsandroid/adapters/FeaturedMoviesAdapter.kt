package com.williamxyshi.njsandroid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.utils.MovieServerAccessObject
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

class FeaturedMoviesAdapter(val vm: MainActivityViewModel, private val context: Context) : RecyclerView.Adapter<FeaturedMoviesAdapter.FeaturedMovieViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedMovieViewHolder {
        return FeaturedMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_featured_movie, parent, false)).apply {
            featuredMoviePoster = itemView.findViewById(R.id.featuredMoviePoster)
            cell = itemView.findViewById(R.id.featuredMoviesContainer)
        }


    }
    override fun onBindViewHolder(holder: FeaturedMovieViewHolder, position: Int) {

        val movie = vm.frontPage.value?.FeaturedMovies?.get(position)

        Glide.with(context?:return).load(movie?.posterurl).into(holder.featuredMoviePoster)

        holder.cell.setOnClickListener{

            MovieServerAccessObject.searchSpecificMovie(movie?.name?:return@setOnClickListener, vm)

        }

    }

    override fun getItemCount(): Int {
        return vm.frontPage.value?.FeaturedMovies?.size?:0
    }


    inner class FeaturedMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var featuredMoviePoster: ImageView

        lateinit var cell: ConstraintLayout
    }


    companion object{
        private const val TAG = "FeaturedMoviesAdapter"
    }
}