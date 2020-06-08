
const express = require("express");
const { check, validationResult} = require("express-validator/check");
const jwt = require("jsonwebtoken");
const router = express.Router();

const auth = require("./../middleware/auth");

const Movie = require("../models/movie_model");


    /**
     * 
     * returns movies to display for the home page
     * hardcoded for now, but in the future return stuff parsed
     * from the database
     * 
     */
router.get("/frontpage", async (req, res) => {
  try {

    A = ["get out", "silent hill", "shining", "paranormal activity" ]



    res.json(A)

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
    const { name, length } = req.body;
    console.log("reached movie" + name + length);

    let movie = await Movie.findOne({
      name
    });
    console.log("reached movie" + movie);

    if(movie != null){

      res.json(movie);

    } else {
      console.log("movie not found");

      movie = new Movie({

       name,
       length

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
      console.log("movie found");
     
      var post = {time: posttime, majorscare: majorscare, description: description};
      console.log("movie found" + post.time);
     
      Movie.findOneAndUpdate({name: name}, {$push: {posts: post}},{new: true}, (err, result) => {
        console.log(err, result);
       })
    

      let movie = await Movie.findOne({
        name
      });
      console.log("movie found as:" + movie);
      res.json(movie);



    } else {
      console.log("movie not found");

      res.send({ message: "error in fetching movie" });


    }





  } catch (e) {
    res.send({ message: "error in fetching movie" });
  }
});














router.get("/test", async (req, res) => {
    try {

      res.json("boom");

    } catch (e) {
      res.send({ message: "Error in Fetching user" });
    }
  });


module.exports = router;
