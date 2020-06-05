const mongoose = require("mongoose");

const Movie = mongoose.Schema({

  name: String,
  length: Number,
  posts: Array
   
});

// export model user with UserSchema
module.exports = mongoose.model("movie", Movie);
 