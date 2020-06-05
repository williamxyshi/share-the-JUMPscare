
const express = require("express");
const { check, validationResult} = require("express-validator/check");
const jwt = require("jsonwebtoken");
const router = express.Router();

const auth = require("./../middleware/auth");

const Movie = require("../models/movie_model");


router.get("/getmovie", async (req, res) => {
  try {

    const { movie_name, length } = req.body;

    let movie = await User.findOne({
      movie_name
    });
    if(movie != null){

    } else {
      
    }





  } catch (e) {
    res.send({ message: "Error in Fetching user" });
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
