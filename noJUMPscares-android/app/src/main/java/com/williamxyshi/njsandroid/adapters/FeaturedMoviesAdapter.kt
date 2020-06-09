package com.williamxyshi.njsandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.williamxyshi.njsandroid.R

class FeaturedMoviesAdapter: RecyclerView.Adapter<FeaturedMoviesAdapter.FeaturedMovieViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedMovieViewHolder {
        return FeaturedMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_featured_movie, parent, false)).apply {
            featuredMoviePoster = itemView.findViewById(R.id.featuredMoviePoster)
            featuredMovieTitle = itemView.findViewById(R.id.featuredMovieTitle)
        }


    }
    override fun onBindViewHolder(holder: FeaturedMovieViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 
    }


    inner class FeaturedMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var featuredMovieTitle: TextView
        lateinit var featuredMoviePoster: ImageView
    }


    companion object{
        private const val TAG = "FeaturedMoviesAdapter"
    }
}