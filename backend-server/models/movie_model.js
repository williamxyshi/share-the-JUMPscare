const mongoose = require("mongoose");

const Movie = mongoose.Schema({

  name: String,
  length: String,
  posterurl: String,
  posts : [{time: String, majorscare: String, description: String, upvotes: Number, useremail: String}]
   
});

// export movie user
module.exports = mongoose.model("movie", Movie);
 