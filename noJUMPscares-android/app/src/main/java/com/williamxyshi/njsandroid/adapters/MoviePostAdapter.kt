package com.williamxyshi.njsandroid.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

import com.williamxyshi.njsandroid.models.retrofitmodels.MovieDataClasses
import com.williamxyshi.njsandroid.utils.WebServerAccessObject
import android.content.DialogInterface
import com.williamxyshi.njsandroid.R




class MoviePostAdapter(val context: Context, val vm: MainActivityViewModel):  RecyclerView.Adapter<MoviePostAdapter.PostViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_detail_post, parent, false)).apply {
            majorScare = itemView.findViewById(R.id.postMajorScare)
            postTime = itemView.findViewById(R.id.postTime)
            postDescription = itemView.findViewById(R.id.movieDescription)

            upvoteCount = itemView.findViewById(R.id.upvoteCount)

            upvoteButton = itemView.findViewById(R.id.upvoteButton)
            downvoteButton = itemView.findViewById(R.id.downvoteButton)

            deleteButton = itemView.findViewById(R.id.deleteButton)
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


        val currentAction = if(post?.upvotedBy?.contains(vm.user.value?.email?:return) == true){
            UPVOTED
        } else if(post?.downvotedBy?.contains(vm.user.value?.email?:return) == true){
            DOWNVOTED
        } else {
            NO_ACTION
        }

        val upvotes = post?.upvotedBy?.size?:0
        val downvotes = post?.downvotedBy?.size?:0


        holder.upvoteCount.text = ( upvotes -  downvotes).toString()

        holder.upvoteButton.setOnClickListener {

            /**
             * unupvote if its upvoted,
             * else upvote the post
             */
            val voteInfo = if(currentAction == UPVOTED){
                MovieDataClasses.VoteInfo(vm.currentMovieDetailWebServer.value?.name?:return@setOnClickListener, post?.time?:return@setOnClickListener, post?.description?: return@setOnClickListener, "unvote")
            } else {
                MovieDataClasses.VoteInfo(vm.currentMovieDetailWebServer.value?.name?:return@setOnClickListener, post?.time?:return@setOnClickListener, post?.description?: return@setOnClickListener, "upvote")
            }

            WebServerAccessObject.votePost(voteInfo, vm)

        }

        holder.downvoteButton.setOnClickListener {

            /**
             * upvote if its downvoted,
             * else downvote the post
             */
            val voteInfo = if(currentAction == DOWNVOTED){
                MovieDataClasses.VoteInfo(vm.currentMovieDetailWebServer.value?.name?:return@setOnClickListener, post?.time?:return@setOnClickListener, post?.description?: return@setOnClickListener, "unvote")
            } else {
                MovieDataClasses.VoteInfo(vm.currentMovieDetailWebServer.value?.name?:return@setOnClickListener, post?.time?:return@setOnClickListener, post?.description?: return@setOnClickListener, "downvote")
            }
            WebServerAccessObject.votePost(voteInfo, vm)

        }

        when(currentAction){
            UPVOTED -> {
                holder.upvoteButton.setColorFilter(context.resources.getColor(R.color.orangeAccent))
                holder.downvoteButton.setColorFilter(null)
            }
            DOWNVOTED -> {
                holder.upvoteButton.setColorFilter(null)

                holder.downvoteButton.setColorFilter(context.resources.getColor(R.color.orangeAccent))

            }
            NO_ACTION  -> {
                holder.upvoteButton.setColorFilter(null)

                holder.downvoteButton.setColorFilter(null)
            }
        }


        if(post?.useremail?:return == vm.user.value?.email?:return){
            holder.deleteButton.visibility = View.VISIBLE

        } else {
            holder.deleteButton.visibility = View.GONE
        }


        holder.deleteButton.setOnClickListener {
            vm.deleteInfo = MovieDataClasses.DeleteInfo(   vm.currentMovieDetailWebServer.value?.name?:return@setOnClickListener, post?.time, post?.useremail)


            val diaBox = AskOption()
            diaBox.show()

        }





    }

    override fun getItemCount(): Int {
        return vm.currentMovieDetailWebServer.value?.posts?.size?:0
    }






    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        lateinit var majorScare: ImageView
        lateinit var postTime: TextView
        lateinit var postDescription: TextView

        lateinit var upvoteCount: TextView
        lateinit var upvoteButton: ImageView
        lateinit var downvoteButton: ImageView

        lateinit var deleteButton: ImageView


    }

    companion object{
        private const val TAG = "MoviePostAdapter"

        private const val UPVOTED = 0
        private const val NO_ACTION = 1
        private const val DOWNVOTED = 2
    }

    private fun AskOption(): AlertDialog {

        return AlertDialog.Builder(context)
            // set message, title, and icon
            .setTitle("Delete")
            .setMessage("Delete post?")
            .setIcon(R.drawable.ic_trash)

            .setPositiveButton("Delete",
                DialogInterface.OnClickListener { dialog, whichButton ->
                    //your deleting code
                    WebServerAccessObject.deletePost(vm.deleteInfo?:return@OnClickListener, vm)

                    dialog.dismiss()
                })
            .setNegativeButton("cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            .create()
    }
}