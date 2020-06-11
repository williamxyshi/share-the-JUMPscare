package com.williamxyshi.njsandroid.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.utils.MovieServerAccessObject
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

class HomePageMovieAdapter (val context: Context, val vm: MainActivityViewModel, val mostLiked: Boolean) : RecyclerView.Adapter<HomePageMovieAdapter.MovieCellViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        return MovieCellViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_home_page_movie, parent, false)).apply {
            moviePoster = itemView.findViewById(R.id.moviePoster)
            scaresCount = itemView.findViewById(R.id.scaresCount)
            cell = itemView.findViewById(R.id.movieCellContainer)
        }
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        val movie = if(mostLiked){
            vm.frontPage.value?.MostLiked?.get(position)
        } else {
            vm.frontPage.value?.MostScares?.get(position)
        }

        val emoji ="\uD83D\uDC7B"

        holder.scaresCount.text = emoji + ": " + movie?.posts?.size

        Glide.with(context).load(movie?.posterurl).into(holder.moviePoster)


        holder.cell.setOnClickListener{
            MovieServerAccessObject.searchSpecificMovie(movie?.name?:return@setOnClickListener, vm)
        }

    }

    override fun getItemCount(): Int {
        return if(mostLiked){
            vm.frontPage.value?.MostLiked?.size?:0
        } else {
            vm.frontPage.value?.MostScares?.size?:0
        }
    }

    inner class MovieCellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var moviePoster: ImageView
        lateinit var scaresCount: TextView

        lateinit var cell: ConstraintLayout
    }


    companion object{
        private const val TAG = "HomePageMovieAdapter"
    }
}