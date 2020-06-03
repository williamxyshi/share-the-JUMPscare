
const express = require("express");
const { check, validationResult} = require("express-validator/check");
const jwt = require("jsonwebtoken");
const router = express.Router();

const auth = require("./../middleware/auth");

const Movie = require("../models/movie_model");


router.get("/me", async (req, res) => {
    try {

      res.json("boom");

    } catch (e) {
      res.send({ message: "Error in Fetching user" });
    }
  });


module.exports = router;
