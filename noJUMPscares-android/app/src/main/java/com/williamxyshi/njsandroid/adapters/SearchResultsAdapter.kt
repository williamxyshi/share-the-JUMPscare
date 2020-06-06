package com.williamxyshi.njsandroid.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.models.retrofitmodels.MovieModel
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

class SearchResultsAdapter(private val vm: MainActivityViewModel, private val context: Context): RecyclerView.Adapter<SearchResultsAdapter.SearchResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_search_result, parent, false)).apply {

            movieTitle = itemView.findViewById(R.id.movieName)
            movieYear = itemView.findViewById(R.id.movieYear)
            moviePoster = itemView.findViewById(R.id.moviePoster)
            movieType = itemView.findViewById(R.id.movieType)
        }
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val movieModel: MovieModel.SearchResult = vm.searchPageResults.value?.Search?.get(position)?:return

        holder.movieTitle.text = movieModel.Title
        holder.movieYear.text = movieModel.Year
        holder.movieType.text = movieModel.Type

        Log.d(TAG, "binding this: ${movieModel}")

        Glide.with(context?:return).load(movieModel.Poster).into(holder.moviePoster)










    }

    override fun getItemCount(): Int {
        return vm.searchPageResults.value?.Search?.size?:0
    }


    inner class SearchResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var movieTitle: TextView
        lateinit var movieYear: TextView
        lateinit var moviePoster: ImageView
        lateinit var movieType: TextView


    }

    companion object{
        private const val TAG = "SearchResultsAdapter"
    }

}