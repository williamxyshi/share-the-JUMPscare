const mongoose = require("mongoose");

const Movie = mongoose.Schema({

  name: String,
  length: Number,
  posts : [{time: String, majorscare: String, description: String}]
   
});

// export movie user
module.exports = mongoose.model("movie", Movie);
 