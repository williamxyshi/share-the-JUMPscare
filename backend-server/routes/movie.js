
const express = require("express");
const { check, validationResult} = require("express-validator/check");
const jwt = require("jsonwebtoken");
const router = express.Router();

const auth = require("./../middleware/auth");

const Movie = require("../models/movie_model");

router.get("/frontpage", async (req, res) => {
  try {

    A = ["get out", "silent hill", "shining", "paranormal activity" ]



    res.json(A)

  } catch (e) {
    res.send({ message: "error in fetching frontpage" });
  }
});


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














router.get("/test", async (req, res) => {
    try {

      res.json("boom");

    } catch (e) {
      res.send({ message: "Error in Fetching user" });
    }
  });


module.exports = router;
