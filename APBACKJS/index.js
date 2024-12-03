const express = require('express');
const cors = require('cors');

require('./src/config/config.js');
require("dotenv").config();

const authRoutes = require('./src/routes/auth.routes');


const app = express();

app.use(cors());

app.use(express.json());
const PORT = process.env.PORT ;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});



