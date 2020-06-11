package com.williamxyshi.njsandroid.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel
import androidx.core.graphics.drawable.DrawableCompat
import android.graphics.drawable.Drawable

import androidx.appcompat.content.res.AppCompatResources




class MoviePostAdapter(val context: Context, val vm: MainActivityViewModel):  RecyclerView.Adapter<MoviePostAdapter.PostViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_detail_post, parent, false)).apply {
            majorScare = itemView.findViewById(R.id.postMajorScare)
            postTime = itemView.findViewById(R.id.postTime)
            postDescription = itemView.findViewById(R.id.movieDescription)
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = vm.currentMovieDetailWebServer.value?.posts?.get(position)

        holder.postTime.text = "time: " + post?.time
        holder.postDescription.text = post?.description?:""

        if(post?.majorscare == "true"){
            holder.majorScare.setImageDrawable(context.resources.getDrawable(R.drawable.ic_warning))

        } else {
            holder.majorScare.setImageDrawable(context.resources.getDrawable(R.drawable.ic_ok))

        }




    }

    override fun getItemCount(): Int {
        return vm.currentMovieDetailWebServer.value?.posts?.size?:0
    }






    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        lateinit var majorScare: ImageView
        lateinit var postTime: TextView
        lateinit var postDescription: TextView


    }

    companion object{
        private const val TAG = "MoviePostAdapter"
    }
}