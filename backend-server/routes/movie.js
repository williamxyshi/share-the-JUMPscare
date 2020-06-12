
const express = require("express");
const { check, validationResult} = require("express-validator/check");
const jwt = require("jsonwebtoken");
const router = express.Router();

const auth = require("./../middleware/auth");

const Movie = require("../models/movie_model");
const User = require("../models/user_model")


    /**
     * 
     * returns movies to display for the home page
     * hardcoded for now, but in the future return stuff parsed
     * from the database
     * 
     */
router.get("/frontpage", async (req, res) => {
  try {

    /**
     * finding the featured movies for the frontpage
     */
    var A = ["Get Out", "Silent Hill", "The Shining", "Paranormal Activity", "Parasite"]
    FeaturedMovies = []

    for(i = 0; i < A.length; i++){
      var name = A[i]
      console.log("searching movies:" + name);


      let movie = await Movie.findOne({
        name
      });

      console.log("movie" + movie.name);

      FeaturedMovies.push(movie)
    }
    
    /**
     * find most scares movies
     * 
     * TODO: Fix this to actually most scares (most posts)
     */
    var A = ["Get Out", "Silent Hill", "The Shining", "Paranormal Activity", "Parasite"]
    MostScares = []

    for(i = 0; i < A.length; i++){
      var name = A[i]
      console.log("searching movies:" + name);


      let movie = await Movie.findOne({
        name
      });

      console.log("movie" + movie.name);

      MostScares.push(movie)
    }

    /**
     * find most liked movies
     * 
     * TODO: Fix this to actually most liked (most likes)
     */
    var A = ["Get Out", "Silent Hill", "The Shining", "Paranormal Activity", "Parasite"]
    MostLiked = []

    for(i = 0; i < A.length; i++){
      var name = A[i]
      console.log("searching movies:" + name);


      let movie = await Movie.findOne({
        name
      });

      console.log("movie" + movie.name);

      MostLiked.push(movie)
    }

    // res.json(FeaturedMovies)
    // res.json(MostScares)

    // res.json(MostLiked)

    res.send({"FeaturedMovies": FeaturedMovies,
              "MostScares": MostScares,
              "MostLiked": MostLiked})

  } catch (e) {
    res.send({ message: "error in fetching frontpage" });
  }
});

/**
 * returns a single video from the database
 * 
 * if doesnt exist, creates a new instance
 */
router.post("/getmovie", async (req, res) => {
  try {

    console.log("reached movie" + req);
    const { name, length, posterurl } = req.body;
    console.log("reached movie" + name + length);

    var pageviews = 1

    let movie = await Movie.findOne({
      name
    });
    console.log("reached db movie" + movie);

    if(movie != null && length != null && name != null){

      /**
       * increase view count of page
       */
      movie.pageviews += 1;
      await movie.save()

      res.json(movie);

    } else {
      console.log("movie not found");

      movie = new Movie({

       name,
       length,
       posterurl,
       pageviews

      });

      movie.save()

      console.log("movie saved" + movie.name + movie.length)

      res.json(movie);

    }





  } catch (e) {
    res.send({ message: "error in fetching movie" });
  }
});

/**
 * adds a post to the movie
 */
router.post("/addpost", auth, async (req, res) => {
  try {

   
    const { name, posttime, majorscare, description } = req.body;
    const user = await User.findById(req.user.id);

    let movie = await Movie.findOne({
      name
    });
    

    console.log("movie found as:" + movie);

    if(movie != null && user != null){

      var alreadyPosted = false
      for(i=0; i<movie.posts.length; i++){
        var currentPost = movie.posts[i]
        if(currentPost.useremail == user.email && currentPost.time == posttime){
          alreadyPosted = true;
          break;
        }
      }
      
      if(alreadyPosted){
        res.send({message: "already posted at this time"})

      } else {

            var post = {time: posttime, majorscare: majorscare, description: description, upvotedBy: [user.email], downvotedBy: [], useremail: user.email};
          
            Movie.findOneAndUpdate({name: name}, {$push: {posts: post}},{new: true}, (err, result) => {
              console.log(err, result);
            })
          

            let movie = await Movie.findOne({
              name
            });
            console.log("movie found as:" + movie);
            res.json(movie);
      }


    } else {
      console.log("movie not found");

      res.send({ message: "error in fetching movie" });

    }





  } catch (e) {
    res.send({ message: "error in fetching movie" + e});
  }
});







router.post("/votepost", auth, async (req, res) => {
  try {

   
    const { name, posttime, description, action } = req.body;
    const user = await User.findById(req.user.id);

    let movie = await Movie.findOne({
      name
    });
    

    console.log("movie found as:" + movie + action);

    if(movie != null && user != null){


      var index = -1

      for( i = 0; i < movie.posts.length; i++){
        var currentPost = movie.posts[i];

        console.log("currentpost" + currentPost.time + currentPost.description);

        if(currentPost.time == posttime && currentPost.description == description){
          index = i
          break;
        }  
      }
      function checkEmail(email) {
        return email != user.email;
      }

      /**
       * post found
       */
      if(index != -1){
        if(action == "upvote"){
          movie.posts[index].upvotedBy = movie.posts[index].upvotedBy.filter(checkEmail)
          movie.posts[index].downvotedBy =  movie.posts[index].downvotedBy.filter(checkEmail)

          movie.posts[index].upvotedBy.push(user.email)

          movie.save()
          res.json(movie);
        }
        else if(action == "unvote"){
          movie.posts[index].upvotedBy = movie.posts[index].upvotedBy.filter(checkEmail)
          movie.posts[index].downvotedBy =  movie.posts[index].downvotedBy.filter(checkEmail)


          movie.save()
          res.json(movie);
        }
        else if(action == "downvote"){
          movie.posts[index].upvotedBy = movie.posts[index].upvotedBy.filter(checkEmail)
          movie.posts[index].downvotedBy =  movie.posts[index].downvotedBy.filter(checkEmail)
          movie.posts[index].downvotedBy.push(user.email)

          movie.save()
          res.json(movie);
        }

        


      } else {
        console.log("post not found");

        res.send({ message: "error in fetching post" });
      }



    } else {
      console.log("movie not found");

      res.send({ message: "error in fetching movie" });
      
    }





  } catch (e) {
    res.send({ message: "error in fetching movie" + e});
  }
});


module.exports = router;
