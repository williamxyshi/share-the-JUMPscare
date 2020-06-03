const mongoose = require("mongoose");

const Movie = mongoose.Schema({
  name: {
    type: String,
    required: true
  },
  length: {
    type: Number,
    required: true
  },
  posts: {
    type: Array,
    default: Date.now()
  }
});

// export model user with UserSchema
module.exports = mongoose.model("movie", Movie);
