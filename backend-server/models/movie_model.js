const mongoose = require("mongoose");

const Movie = mongoose.Schema({

  name: String,
  length: String,
  posterurl: String,
  pageviews: Number,
  posts : [{time: String, majorscare: String, description: String, upvotedBy: [], downvotedBy: [], useremail: String}]
   
});

// export movie user
module.exports = mongoose.model("movie", Movie);
