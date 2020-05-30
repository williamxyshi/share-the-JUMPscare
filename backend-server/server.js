// step 8 on https://dev.to/dipakkr/implementing-authentication-in-nodejs-with-express-and-jwt-codelab-1-j5i

const express = require("express");
const bodyParser = require("body-parser");

const user = require("./routes/user");

const app = express();

const db = require("./models");

db.mongoose
  .connect(`mongodb://localhost:27017/nojumpscaresdb`, {
    useNewUrlParser: true,
    useUnifiedTopology: true
  })
  .then(() => {
    console.log("Successfully connect to MongoDB.");
  })
  .catch(err => {
    console.error("Connection error", err);
    process.exit();
  });

// PORT
const PORT = process.env.PORT || 3000;

//middleware
app.use(bodyParser.json());

app.get("/", (req, res) => {
  res.json({ message: "base route response" });
});

app.use("/user", user);

app.listen(PORT, (req, res) => {
  console.log(`Server Started at http://localhost:${PORT}`);
});

