const mongoose = require("mongoose");

const Movie = mongoose.Schema({

  name: String,
  length: Number,
  posts : { type : Array , "default" : [] }
   
});

// export movie user
module.exports = mongoose.model("movie", Movie);
 