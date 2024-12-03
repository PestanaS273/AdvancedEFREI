require("dotenv").config();

module.exports = {
    db: {
        database: process.env.DB_NAME,
        user: process.env.DB_USER,
        password: process.env.DB_PASS,
        dialect: process.env.DIALECT,
        host: process.env.HOST,
    },
    authentication: {
        jwtSecret: process.env.JWT_SECRET || "secret",
    },
}

